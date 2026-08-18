@Component
struct BrokenComponent {
  build() {
    Column() {
      Text('missing close')
  }
}

@Extend(Text)
function brokenModifier(): void {
  .fontSize(
}
