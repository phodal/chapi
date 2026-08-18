import { UIAbility } from '@kit.AbilityKit';

@Entry
@Component
struct CounterPage {
  @State count: number = 0;

  build() {
    Column() {
      Text(this.count.toString())
      Button('Increment')
        .onClick(() => this.count++)
    }
  }
}
