#include "code.h"

using namespace Gateways;
using namespace Repositories;

int main(int argc, char const *argv[])
{
	router = new FakeRouter();
	AggregateRootB* b = new AggregateRootB();
	b->Init();
	return 0;
}