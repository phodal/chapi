package chapi.ast.cast

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

internal class CFullIdentListenerTest {

    @Test
    fun allGrammarUnderResources() {
        val content = this::class.java.getResource("/grammar")!!.toURI()
//        val content = "/Users/phodal/Downloads/redis-unstable/deps/jemalloc/src"
        File(content).walkTopDown().forEach {
            if (it.isFile && (it.extension == "c" || it.extension == "h")) {
                val start = System.currentTimeMillis()
                println("Analyse ${it.path}")
                CAnalyser().analysis(it.readText(), it.name)
                val end = System.currentTimeMillis()
                val seconds = (end - start) / 1000
                println("cost ${end - start}ms")
            }
        }
    }

    @Test
    internal fun shouldIdentifyImport() {
        val code = """
#include <stdio.h>

static RedisModuleType *MemAllocType;

#define MAX_DB 16
"""
        val codeFile = CAnalyser().analysis(code, "helloworld.c")

        assertEquals(codeFile.Imports.size, 1)
        assertEquals(codeFile.Imports[0].Source, "stdio.h")
    }

    @Test
    internal fun shouldIdentifyStructName() {
        val code = """
struct list_el {
   int val;
};
"""
        val codeFile = CAnalyser().analysis(code, "helloworld.c")

        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].NodeName, "list_el")
    }

    @Test
    internal fun shouldIdentifyStructPropertyName() {
        val code = """
struct list_el {
   int val;
};
"""
        val codeFile = CAnalyser().analysis(code, "helloworld.c")

        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].Fields.size, 1)
        assertEquals(codeFile.DataStructures[0].Fields[0].TypeType, "int")
        assertEquals(codeFile.DataStructures[0].Fields[0].TypeValue, "val")
    }

    @Test
    internal fun shouldIdentifyStructFunction() {
        val code = """
struct Person {
    int age;
};

int is_old(Person* p, int age) {
    return p->age > 60;
}
"""
        val codeFile = CAnalyser().analysis(code, "helloworld.c")

        assertEquals(codeFile.DataStructures.size, 1)
    }

    @Test
    internal fun shouldIdentifyStructFunctionPoint() {
        val code = """
struct list_el {
   int val;
   void (*func1)(void);
};
"""
        val codeFile = CAnalyser().analysis(code, "helloworld.c")

        assertEquals(codeFile.DataStructures.size, 1)
    }

    @Test
    internal fun shouldIdentifyAnonymousStruct() {
        val code = """
struct {
   int i;
   int j;
}  myVariableName;
"""
        val codeFile = CAnalyser().analysis(code, "helloworld.c")
    }

    @Test
    internal fun shouldIdentifyNestedStruct() {
        val code = """
struct Info{
    char name[30];
    int age;
    struct {
        char area_name[39];
        int house_no;
        char district[39];
    } address; // <<< here it is now
};
"""
        val codeFile = CAnalyser().analysis(code, "helloworld.c")
    }

    @Test
    internal fun shouldIdentifyFunctionName() {
        val code = """
int is_old(Person* p) {
    return p->age > 60;
}
"""
        val codeFile = CAnalyser().analysis(code, "helloworld.c")
        assertEquals(codeFile.DataStructures[0].Functions[0].Name, "is_old")
    }

    @Test
    internal fun shouldIdentifyFunctionSingleParameter() {
        val code = """
int is_old(Person* p) {
    return p->age > 60;
}
        """
        val codeFile = CAnalyser().analysis(code, "helloworld.c")
        val parameters = codeFile.DataStructures[0].Functions[0].Parameters
        assertEquals(parameters.size, 1)
        assertEquals(parameters[0].TypeType, "Person*")
        assertEquals(parameters[0].TypeValue, "p")
    }

    @Test
    internal fun shouldIdentifyFunctionMultiParameters() {
        val code = """
int is_old(Person* p, int a, double b) {
    return p->age > 60;
}
        """
        val codeFile = CAnalyser().analysis(code, "helloworld.c")
        val parameters = codeFile.DataStructures[0].Functions[0].Parameters
        assertEquals(parameters.size, 3)
        assertEquals(parameters[0].TypeType, "Person*")
        assertEquals(parameters[0].TypeValue, "p")

        assertEquals(parameters[1].TypeType, "int")
        assertEquals(parameters[1].TypeValue, "a")

        assertEquals(parameters[2].TypeType, "double")
        assertEquals(parameters[2].TypeValue, "b")
    }

    @Test
    internal fun shouldIdentifyForwardDeclaration() {
        val code = """
struct context;

struct funcptrs{
  void (*func0)(struct context *ctx);
  void (*func1)(void);
};

struct context{
    struct funcptrs fps;
}; 
"""
        val codeFile = CAnalyser().analysis(code, "helloworld.c")
        assertEquals(codeFile.DataStructures.size, 2)
    }

    @Test
    internal fun shouldIdentifyForwardDeclarationSelf() {
        val code = """
struct element;
typedef struct {
    int value;
    // Use of the forward declaration
    struct element *next;
} element; // Complete definition
"""
        val codeFile = CAnalyser().analysis(code, "helloworld.c")
        assertEquals(codeFile.DataStructures.size, 1)

        val elementDs = codeFile.DataStructures[0]
        assertEquals(elementDs.NodeName, "element")
        assertEquals(elementDs.Fields.size, 2)
        assertEquals(elementDs.Fields[0].TypeType, "int")
        assertEquals(elementDs.Fields[0].TypeValue, "value")
        assertEquals(elementDs.Fields[1].TypeType, "struct element")
        assertEquals(elementDs.Fields[1].TypeValue, "")
    }

    @Test
    fun shouldIdentifyFirstFunctionCall() {
        val code = """
            void aX(void);
            int a1(int param1);
            int a2(int param1, param2);
            void a3();
            void a3(void);
            
            int f(int arg1, char arg2)
            {
                a1(arg1);
                a2(arg1, arg2);
                a3();
            }
            """.trimIndent()

        val codeFile = CAnalyser().analysis(code, "helloworld.c")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions.size, 1)
        val functionCalls = codeFile.DataStructures[0].Functions[0].FunctionCalls
        assertEquals(functionCalls.size, 3)
        assertEquals(functionCalls[0].FunctionName, "a1")
        assertEquals(functionCalls[0].Parameters[0].TypeValue, "arg1")

        assertEquals(functionCalls[1].FunctionName, "a2")
        assertEquals(functionCalls[1].Parameters[0].TypeValue, "arg1")
        assertEquals(functionCalls[1].Parameters[1].TypeValue, "arg2")

        assertEquals(functionCalls[2].FunctionName, "a3")
        assertEquals(functionCalls[2].Parameters.size, 0)
    }

    @Test
    fun shouldIdentifyMultipleInclude() {
        val code = """
            #include <stdio.h>
            #include <string.h>
            #include <stdlib.h>
            #include "redismodule.h"
            #include <sys/mman.h>
            #include <float.h>
            """.trimIndent()

        val codeFile = CAnalyser().analysis(code, "helloworld.c")
        assertEquals(codeFile.Imports.size, 6)
    }

    @Test
    fun readlWorld() {
        val code = """
            // https://stackoverflow.com/questions/12642830/can-i-define-a-function-inside-a-c-structure
            #include <stdio.h>
            #include <string.h>
            #include <stdlib.h>

            struct point
            {
                int x;
                int y;
                void (*print)(const struct point*);
            };

            void print_x(const struct point* p)
            {
                printf("x=%d\n", p->x);
            }

            void print_y(const struct point* p)
            {
                printf("y=%d\n", p->y);
            }

            int main(void)
            {
                struct point p1 = { 2, 4, print_x };
                struct point p2 = { 7, 1, print_y };

                p1.print(&p1);
                p2.print(&p2);

                return 0;
            }
            """.trimIndent()

        val codeFile = CAnalyser().analysis(code, "helloworld.c")
        assertEquals(codeFile.DataStructures.size, 2)

        assertEquals(codeFile.DataStructures[0].NodeName, "default")
        assertEquals(codeFile.DataStructures[1].NodeName, "point")

        assertEquals(codeFile.DataStructures[0].Functions.size, 3)
        assertEquals(codeFile.DataStructures[0].Functions[0].Name, "print_x")
    }

    @Test
    fun shouldEnableMacroInFunction() {
        val code = """
            int TestCtxFlags(RedisModuleCtx *ctx, RedisModuleString **argv, int argc) {
                #undef FAIL
                #define FAIL(msg)        \
                    {                    \
                        ok = 0;          \
                        errString = msg; \
                        goto end;        \
                    }
                    
                    if (flags & REDISMODULE_CTX_FLAGS_AOF) FAIL("AOF Flag was set")
                    RedisModule_Call(ctx, "config", "ccc", "set", "appendonly", "yes");
                    while(RedisModule_Scan(ctx, cursor, scan_strings_callback, &pd));
                end:
                    /* Revert config changes */
            }
            """.trimIndent()

        val codeFile = CAnalyser().analysis(code, "helloworld.c")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions.size, 1)
    }

    @Test
    fun shouldSupportForCallInFunction() {
        val code = """
            typedef struct mq_msg_s mq_msg_t;
            struct mq_msg_s {
            	mq_msg(mq_msg_t)	link;
            };
            """.trimIndent()

        val codeFile = CAnalyser().analysis(code, "helloworld.c")
        assertEquals(codeFile.DataStructures.size, 2)
    }

    @Test
    fun shouldHandleForMultipleMacroWithId() {
        val code = """
            TEST_BEGIN(test_prof_thread_name_threaded) {
            	TEST_FFS(unsigned, u, u);
            	TEST_FFS(unsigned long, lu, lu, "lu");
            }
            TEST_END

            #undef NTHREADS
            #undef NRESET

            int
            main(void) {
            	return test(
            	    test_prof_thread_name_validation,
            	    test_prof_thread_name_threaded);
            }
            """.trimIndent()

        val codeFile = CAnalyser().analysis(code, "helloworld.c")
        assertEquals(codeFile.DataStructures.size, 1)
    }

    @Test
    fun shouldSupportForMacroConcat() {
        val code = """
            static size_t
            tcache_bytes_read(void) {
            	mallctl("stats.arenas." STRINGIFY(MALLCTL_ARENAS_ALL) ".tcache_bytes", &tcache_bytes, &sz, NULL, 0);
            }
            """.trimIndent()

        val codeFile = CAnalyser().analysis(code, "helloworld.c")
        assertEquals(codeFile.DataStructures.size, 1)
    }

    @Test
    fun shouldHandleForKeywordArg() {
        val code = """
            void hello() {
                va_arg(ap, char *);
            }
            
            #include "test/jemalloc_test.h"
            #include "jemalloc/internal/mpsc_queue.h"

            mpsc_queue_proto(static);
            
            #define ERR(e)		e, #e
            """.trimIndent()

        val codeFile = CAnalyser().analysis(code, "helloworld.c")
        assertEquals(codeFile.DataStructures.size, 1)
    }

    @Test
    fun shouldHandleMacroInStructure() {
        val code = """
            #define KUMAX(x)	((uintmax_t)x##ULL)
            typedef rb_tree(node_t) unsummarized_tree_t;
            rb_gen(static UNUSED, unsummarized_tree_);
                
            struct node_s {
            #define NODE_MAGIC 0x9823af7e
            	uint32_t magic;
            	heap_link_t link;
            	uint64_t key;
            };            
            """.trimIndent()

        val codeFile = CAnalyser().analysis(code, "helloworld.c")
        assertEquals(codeFile.DataStructures.size, 1)
    }

    @Test
    fun shouldHandleMacroInDecl() {
        val code = """
            TEST_BEGIN(test_junk_alloc_free) {
            size_t sizevals[] = {
            		1, 8, 100, 1000, 100*1000
            #if LG_SIZEOF_PTR == 3
            		    , 10 * 1000 * 1000
            #endif
            	};
            	size_t lg_alignvals[] = {
            		0, 4, 10, 15, 16, LG_PAGE
            #if LG_SIZEOF_PTR == 3
            		    , 20, 24
            #endif
            	};
            
                CTL_M2_GET("stats.arenas.0.dss", i, &dss, const char *);
            }
            END_TEST
            
            #if 0
            #define TRACE_HOOK(fmt, ...) malloc_printf(fmt, __VA_ARGS__)
            #else
            #define TRACE_HOOK(fmt, ...)
            #endif
            
            size_t n = malloc_snprintf(&buf[i], buflen-i, "%"FMTu64, t0 / t1);
            
            const char filename_prefix[] = TEST_PREFIX ".";
            
            ph_gen(, edata_avail, edata_t, avail_link, edata_esnead_comp)
            """.trimIndent()

        val codeFile = CAnalyser().analysis(code, "helloworld.c")
        assertEquals(codeFile.DataStructures.size, 1)
    }

    @Test
    fun shouldHandleMacroInFunc() {
        val code = """
            static const ctl_named_node_t stats_arenas_i_mutexes_node[] = {
                #define OP(mtx) {NAME(#mtx), CHILD(named, stats_arenas_i_mutexes_##mtx)},
                MUTEX_PROF_ARENA_MUTEXES
                #undef OP
            };
            
            void os_pages_unmap(void *addr, size_t size) {
            	assert(ALIGNMENT_ADDR2BASE(addr, os_page) == addr);
            	assert(ALIGNMENT_CEILING(size, os_page) == size);

            #ifdef _WIN32
            	if (VirtualFree(addr, 0, MEM_RELEASE) == 0)
            #else
            	if (munmap(addr, size) == -1)
            #endif
            	{
            		char buf[BUFERROR_BUF];

            		buferror(get_errno(), buf, sizeof(buf));
            		malloc_printf("<jemalloc>: Error in "
            #ifdef _WIN32
            		    "VirtualFree"
            #else
            		    "munmap"
            #endif
            		    "(): %s\n", buf);
            		if (opt_abort) {
            			abort();
            		}
            	}
            }
            """.trimIndent()

        val codeFile = CAnalyser().analysis(code, "helloworld.c")
        assertEquals(codeFile.DataStructures.size, 1)
    }

    @Test
    fun shouldHandleForMacroForBrokenCondition() {
        val code = """
            static const ctl_named_node_t stats_mutexes_node[] = {
            #define OP(mtx) {NAME(#mtx), CHILD(named, stats_mutexes_##mtx)},
            MUTEX_PROF_GLOBAL_MUTEXES
            #undef OP
            	{NAME("reset"),		CTL(stats_mutexes_reset)}
            };
            #undef MUTEX_PROF_DATA_NODE
            
            static void
            os_pages_unmap(void *addr, size_t size) {
            	assert(ALIGNMENT_ADDR2BASE(addr, os_page) == addr);
            	assert(ALIGNMENT_CEILING(size, os_page) == size);

            #ifdef _WIN32
            	if (VirtualFree(addr, 0, MEM_RELEASE) == 0)
            #else
            	if (munmap(addr, size) == -1)
            #endif
            	{
            		char buf[BUFERROR_BUF];

            		buferror(get_errno(), buf, sizeof(buf));
            		malloc_printf("<jemalloc>: Error in "
            #ifdef _WIN32
            		    "VirtualFree"
            #else
            		    "munmap"
            #endif
            		    "(): %s\n", buf);
            		if (opt_abort) {
            			abort();
            		}
            	}
            }
            """.trimIndent()

        val codeFile = CAnalyser().analysis(code, "helloworld.c")
        assertEquals(codeFile.DataStructures.size, 1)
    }
}
