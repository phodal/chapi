package chapi.ast.cast

import chapi.domain.core.CodeContainer
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals


internal class CFullIdentListenerTest {

    @Test
    fun allGrammarUnderResources() {
        val content = this::class.java.getResource("/grammar")!!.toURI()
//        val content = "/Users/phodal/Downloads/redis-unstable/deps/lua"
        val totalStart = System.currentTimeMillis()
        runBlocking {
            val analyser = CAnalyser()
            val fileFlow = File(content).walkTopDown().asFlow()
            fileFlow.mapNotNull {
                if (it.isFile && (it.extension == "h")) {
                    analyser.addSource(it.readText())
                }
            }.collect()

            val result: MutableList<CodeContainer> = mutableListOf()
            fileFlow.mapNotNull {
                if (it.isFile && (it.extension == "c")) {
                    val start = System.currentTimeMillis()
                    println("Analyse ${it.path}")
                    val analysis = analyser.analysis(it.readText(), it.name)
                    val end = System.currentTimeMillis()
                    println("cost ${end - start}ms")
                    result += analysis
                } else {
                    null
                }
            }.collect()

            // log to file
            File("result.json").writeText(Json.encodeToString(result))
        }

        val totalEnd = System.currentTimeMillis()
        println("total cost ${totalEnd - totalStart}ms")
    }

    @Test
    internal fun shouldIdentifyImport() {
        val code = """
#include <stdio.h>

static RedisModuleType *MemAllocType;

#define MAX_DB 16
"""
        val codeFile = CAnalyser().analysis(code, "helloworld.c")

        assertEquals(codeFile.Imports.size, 0)
//        assertEquals(codeFile.Imports[0].Source, "stdio.h")
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
    internal fun shouldIdentifyStructWithPointer() {
        val code = """
typedef struct Mbuffer {
  char *buffer;
  size_t n;
  size_t buffsize;
} Mbuffer;

"""
        val codeFile = CAnalyser().analysis(code, "helloworld.c")

        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].Fields.size, 3)
        assertEquals(codeFile.DataStructures[0].Fields[0].TypeType, "char")
        assertEquals(codeFile.DataStructures[0].Fields[0].TypeValue, "*buffer")
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
        assertEquals(codeFile.DataStructures.size, 1)
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
        assertEquals(codeFile.DataStructures.size, 1)
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
            #include LUA_USER_H
            """.trimIndent()

        val codeFile = CAnalyser().analysis(code, "helloworld.c")
        assertEquals(codeFile.Imports.size, 0)
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
            #define TEST_BEGIN(f)							\
            static void								\
            f(void) {								\
            	p_test_init(#f);

            #define TEST_END							\
            	goto label_test_end;						\
            label_test_end:								\
            	p_test_fini();							\
            }

            #define TEST_FFS(t, suf, test_suf, pri) do {				\
                for (unsigned i = 0; i < sizeof(t) * 8; i++) {			\
                    for (unsigned j = 0; j <= i; j++) {			\
                        for (unsigned k = 0; k <= j; k++) {		\
                            t x = (t)1 << i;			\
                            x |= (t)1 << j;				\
                            x |= (t)1 << k;				\
                            expect_##test_suf##_eq(ffs_##suf(x), k,	\
                                "Unexpected result, x=%"pri, x);	\
                        }						\
                    }							\
                }								\
            } while(0)

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
            #define STRINGIFY_HELPER(x) #x
            #define STRINGIFY(x) STRINGIFY_HELPER(x)

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
            #include "test/jemalloc_test.h"
            
            typedef struct node_s node_t;
            
            struct node_s {
            #define NODE_MAGIC 0x9823af7e
            	uint32_t magic;
            	heap_link_t link;
            	uint64_t key;
            };            
            """.trimIndent()

        val codeFile = CAnalyser().analysis(code, "helloworld.c")
        assertEquals(codeFile.DataStructures.size, 2)
    }

    @Test
    fun shouldHandleMacroInDecl() {
        val code = """
            #define TEST_PREFIX "test_prefix"
            const char filename_prefix[] = TEST_PREFIX ".";
            """.trimIndent()

        val codeFile = CAnalyser().analysis(code, "helloworld.c")
        assertEquals(codeFile.DataStructures.size, 0)
    }

    @Test
    fun shouldHandleMacroInFunc() {
        val code = """
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

    @Test
    fun shouldHandleMacroInArray() {
        val code = """
            static char log_filename[
            #ifdef JEMALLOC_PROF
                PATH_MAX +
            #endif
                1];
            """.trimIndent()

        val codeFile = CAnalyser().analysis(code, "helloworld.c")
        assertEquals(codeFile.DataStructures.size, 0)
    }

    @Test
    fun shouldHandleMacroIArrayDefine() {
        val code = """
            static char log_filename[
            #ifdef JEMALLOC_PROF
                PATH_MAX +
            #endif
                1];
            """.trimIndent()

        val codeFile = CAnalyser().analysis(code, "helloworld.c")
        assertEquals(codeFile.DataStructures.size, 0)
    }

    @Test
    fun shouldEnableMacroInTypedefStruct() {
        val code = """
            typedef struct {
                uint64_t ns;
            #ifdef JEMALLOC_DEBUG
                uint32_t magic; /* Tracks if initialized. */
            #endif
            } nstime_t;
            """.trimIndent()

        val codeFile = CAnalyser().analysis(code, "helloworld.c")
        assertEquals(codeFile.DataStructures.size, 1)
    }

    @Test
    fun shouldSupportForAsmBody() {
        val code = """
            #define lua_number2int(i,d)   __asm fld d   __asm fistp i
            """.trimIndent()

        val codeFile = CAnalyser().analysis(code, "helloworld.c")
        assertEquals(codeFile.DataStructures.size, 0)
    }

    @Test
    fun shouldSupportMacroStruct() {
        val code = """
            #define LUAI_USER_ALIGNMENT_T	union { double u; void *s; long l; }
            """.trimIndent()

        val codeFile = CAnalyser().analysis(code, "helloworld.c")
        assertEquals(codeFile.DataStructures.size, 0)
    }

    @Test
    fun shouldHandleMacroCall() {
        val code = """
            #define Protect(x)	{ L->savedpc = pc; {x;}; base = L->base; }
            
            void hello() {
                Protect(luaV_gettable(L, &g, rb, ra));
                
                Protect(
                  if (!call_binTM(L, rb, luaO_nilobject, ra, TM_LEN))
                    luaG_typeerror(L, rb, "get length of");
                )
            }
            """.trimIndent()

        val codeFile = CAnalyser().analysis(code, "helloworld.c")
        assertEquals(codeFile.DataStructures.size, 1)
    }

    @Test
    fun shouldHandleForMacroInCplusplus() {
        val code = """
            #ifndef HDR_TESTS_H
            #define HDR_TESTS_H
            
            #include "hdr_histogram.h"
            
            #ifdef __cplusplus
            extern "C" {
            #endif
            
            int32_t counts_index_for(const struct hdr_histogram* h, int64_t value);
            int hdr_encode_compressed(struct hdr_histogram* h, uint8_t** compressed_histogram, size_t* compressed_len);
            int hdr_decode_compressed(uint8_t* buffer, size_t length, struct hdr_histogram** histogram);
            void hdr_base64_decode_block(const char* input, uint8_t* output);
            void hdr_base64_encode_block(const uint8_t* input, char* output);
            
            #ifdef __cplusplus
            }
            #endif
            
            #endif

            """.trimIndent()

        val codeFile = CAnalyser().analysis(code, "helloworld.c")
        assertEquals(codeFile.DataStructures.size, 0)
    }
}

