@ObservedV2
class V2State {
  @Trace count: number = 0;
}

@ComponentV2
struct V2Counter {
  @Local localCount: number = 0;
  @Param title: string = '';
  @Event onChange: (value: number) => void = () => {};
  @Provider('theme') theme: string = 'light';

  @Computed
  get doubled(): number {
    return this.localCount * 2;
  }

  @Monitor('localCount')
  onLocalCountChanged(): void {
    this.onChange(this.localCount);
  }

  build() {
    Column() {
      Text(`${this.title}: ${this.doubled}`)
      Button('Increment')
        .onClick(() => this.localCount++)
    }
  }
}
