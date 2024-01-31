#define MAXASIZE	(1 << MAXBITS)
#define hashpow2(t,n)      (gnode(t, lmod((n), sizenode(t))))
#define hashstr(t,str)  hashpow2(t, (str)->tsv.hash)
#define hashboolean(t,p)        hashpow2(t, p)
#define hashmod(t,n)	(gnode(t, ((n) % ((sizenode(t)-1)|1))))
#define hashpointer(t,p)	hashmod(t, IntPoint(p))
#define numints		cast_int(sizeof(lua_Number)/sizeof(int))
#define dummynode		(&dummynode_)

#ifndef MINSTRTABSIZE
#define MINSTRTABSIZE	32
#endif

#ifndef LUA_MINBUFFER
#define LUA_MINBUFFER	32
#endif

#ifndef lua_lock
#define lua_lock(L)     ((void) 0)
#define lua_unlock(L)   ((void) 0)
#endif

