#include <iostream>

namespace Domain {
class Entity
{
public:
	int Id;
};

class AggregateRoot: public Entity
{
};


class ValueObject
{
};

class Provider
{

};

class Router: public Provider
{
public:
	virtual int Selete() = 0;
};

static Router* router;

class ValueObjectC: public ValueObject
{
public:
	ValueObjectC(){};
	~ValueObjectC(){};
	
};

class ValueObjectD: public ValueObjectC
{
public:
	ValueObjectD(){};
	~ValueObjectD(){};
	
};

class EntityB: public Entity
{
public:
	EntityB(){};
	~EntityB(){};
	ValueObjectD* vo_d; 
	void init(){
		vo_d = new ValueObjectD();
		std::cout << "entity b init" << "\n";
	};
};

class EntityC: public EntityB
{
public:
	EntityC(){};
	~EntityC(){};
	void init(){

	};
};

class AggregateRootA: public AggregateRoot
{
public:
	AggregateRootA(){};
	~AggregateRootA(){};
	EntityB* entity_b;
	EntityC* entity_c;
	ValueObjectC* vo_c; 
	void Init(){
		entity_b = new EntityB();
		entity_b->init();
		router->Selete();
		entity_c = new EntityC();
		entity_c->init();
	};
};

class AggregateRootB: public AggregateRootA
{
public:
	AggregateRootB(){};
	~AggregateRootB(){};
};

}