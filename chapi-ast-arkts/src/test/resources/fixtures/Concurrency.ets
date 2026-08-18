@Sendable
class WorkItem {
  readonly value: number;

  constructor(value: number) {
    this.value = value;
  }
}

@Concurrent
function doubleValue(item: WorkItem): number {
  return item.value * 2;
}

async function execute(item: WorkItem): Promise<number> {
  return await Promise.resolve(doubleValue(item));
}
