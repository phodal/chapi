// AllInOne.ets
// Parser-oriented positive fixture: symbols imported from local modules do not
// need to exist for syntax-only tests, but the source itself should remain
// valid ArkTS/ArkUI syntax.

import UIAbility from '@ohos.app.ability.UIAbility';
import { UIAbility as KitUIAbility, Context } from '@kit.AbilityKit';
import * as AbilityKit from '@kit.AbilityKit';
import type { UserDTO } from './types';
import lazy { LazyFeature as DeferredFeature } from './lazy-feature';
import { featureFlag as renamedFeatureFlag } from './feature';
import './bootstrap';

export { createLogger as logger } from './logger';
export * from './shared';

// -----------------------------------------------------------------------------
// 1. Declarations, literals, aliases, unions, arrays, records, enums
// -----------------------------------------------------------------------------

export const APP_NAME: string = 'Chapi ArkTS All-in-One';
export let bootCount: number = 0;

const inferredName = 'ArkTS';
const HEX_MASK: number = 0xff;
const LARGE_COUNTER: bigint =
  999999999999999999999999999999999999999999999999n;
const NAME_PATTERN: RegExp = /arkts/gi;
const MATRIX: number[][] = [
  [1, 0],
  [0, 1]
];
const SCORE_BY_TOOL: Record<string, number> = {
  'antlr': 100,
  'chapi': 95
};

export enum LoadState {
  Idle,
  Loading = 1,
  Ready,
  Failed = 99
}

export type Identifier = string | number;
export type Nullable<T> = T | null | undefined;
export type ValueHandler<T> = (value: T, index: number) => void;

export class Point {
  x: number = 0;
  y: number = 0;
}

const ORIGIN: Point = { x: 0, y: 0 };

// -----------------------------------------------------------------------------
// 2. Custom annotation declaration and annotation use
// -----------------------------------------------------------------------------

export @interface FeatureMeta {
  name: string;
  version: number = 1;
  tags: string[] = [];
}

@FeatureMeta({
  name: 'all-in-one',
  version: 1,
  tags: ['arkts', 'arkui', 'parser']
})
export class AnnotatedService {
  @FeatureMeta({ name: 'start' })
  start(): void {
    bootCount++;
  }
}

export default class DefaultArkTSService {
  readonly name: string = APP_NAME;

  run(): void {
    new AnnotatedService().start();
  }
}

// -----------------------------------------------------------------------------
// 3. Interfaces, abstract classes, inheritance, accessors, generics
// -----------------------------------------------------------------------------

export interface Identifiable {
  readonly id: string;
}

export interface Describable extends Identifiable {
  describe(prefix?: string): string;
}

export interface Repository<T extends Identifiable> {
  findById(id: string): Promise<T | null>;
  save(entity: T): Promise<void>;
  all(): T[];
}

export abstract class BaseEntity implements Describable {
  static readonly entityKind: string = 'entity';

  readonly id: string;
  protected createdAt: number;
  private enabled: boolean = true;

  constructor(id: string, createdAt: number = Date.now()) {
    this.id = id;
    this.createdAt = createdAt;
  }

  get active(): boolean {
    return this.enabled;
  }

  set active(value: boolean) {
    this.enabled = value;
  }

  abstract describe(prefix?: string): string;
}

@Observed
export class TodoItem extends BaseEntity {
  @Track title: string;
  @Track done: boolean;
  tags: string[];
  metadata: Record<string, string>;

  constructor(
    id: string,
    title: string,
    done: boolean = false,
    tags: string[] = []
  ) {
    super(id);
    this.title = title;
    this.done = done;
    this.tags = tags;
    this.metadata = {};
  }

  override describe(prefix: string = 'todo'): string {
    return `${prefix}:${this.id}:${this.title}:${this.done}`;
  }

  public toggle(): void {
    this.done = !this.done;
  }
}

export class Box<T = string> {
  private value: T;

  constructor(value: T) {
    this.value = value;
  }

  get current(): T {
    return this.value;
  }

  set current(value: T) {
    this.value = value;
  }

  map<R>(mapper: (value: T) => R): Box<R> {
    return new Box<R>(mapper(this.value));
  }

  static from<U>(value: U): Box<U> {
    return new Box<U>(value);
  }
}

@FeatureMeta({ name: 'repository', version: 2 })
export class InMemoryRepository<T extends Identifiable>
  implements Repository<T> {
  private values: T[] = [];

  async findById(id: string): Promise<T | null> {
    const matched: T | undefined =
      this.values.find((item: T): boolean => item.id === id);
    return matched ?? null;
  }

  async save(entity: T): Promise<void> {
    const index: number =
      this.values.findIndex((item: T): boolean => item.id === entity.id);

    if (index >= 0) {
      this.values[index] = entity;
    } else {
      this.values.push(entity);
    }
  }

  all(): T[] {
    return this.values.slice();
  }
}

// -----------------------------------------------------------------------------
// 4. Functions: optional/default/rest parameters, overloads, closures, async
// -----------------------------------------------------------------------------

export function formatValue(value: number): string;
export function formatValue(value: string): string;
export function formatValue(value: number | string): string {
  return `${value}`;
}

export function sum(...numbers: number[]): number {
  let result: number = 0;
  for (const value of numbers) {
    result += value;
  }
  return result;
}

export function last<T>(values: T[]): T | undefined {
  return values.length === 0 ? undefined : values[values.length - 1];
}

export function createCounter(start: number = 0): () => number {
  let current: number = start;
  return (): number => {
    current++;
    return current;
  };
}

export function greet(name?: string): string {
  return name == undefined ? 'Hello!' : `Hello, ${name}!`;
}

export async function loadTodo(
  repository: Repository<TodoItem>,
  id: string
): Promise<TodoItem | null> {
  try {
    return await repository.findById(id);
  } catch (error) {
    console.error(`loadTodo failed: ${error}`);
    return null;
  } finally {
    bootCount++;
  }
}

export async function activateDynamicFeature(): Promise<void> {
  const feature = await import('./feature-runtime');
  feature.activate();
}

// -----------------------------------------------------------------------------
// 5. Statements and expressions
// -----------------------------------------------------------------------------

export function exerciseStatements(
  values: number[],
  initialState: LoadState
): number {
  let total: number = 0;
  let index: number = 0;

  for (const value of values) {
    if (value < 0) {
      continue;
    }
    total += value;
    if (total > 100) {
      break;
    }
  }

  for (let cursor: number = 0; cursor < values.length; cursor++) {
    total += values[cursor];
  }

  while (index < values.length) {
    total += values[index];
    index++;
  }

  do {
    total--;
  } while (total > 100);

  switch (initialState) {
    case LoadState.Idle:
      total += 1;
      break;
    case LoadState.Loading:
      total += 2;
      break;
    case LoadState.Ready:
      total += 3;
      break;
    default:
      total = -1;
  }

  const even: boolean = (total & 1) === 0;
  return even ? total : total + 1;
}

export function describeNullable(todo: TodoItem | null): string {
  const title: string = todo?.title ?? 'missing';
  return todo == null ? title : todo!.describe('selected');
}

export function normalizeIdentifier(value: Identifier): string {
  if (typeof value === 'number') {
    return value.toString();
  }
  return value.toUpperCase();
}

export function asBaseEntity(todo: TodoItem): BaseEntity {
  return todo as BaseEntity;
}

export function isTodo(entity: BaseEntity): boolean {
  return entity instanceof TodoItem;
}

export function requireTodo(todo: TodoItem | null): TodoItem {
  if (todo == null) {
    throw new Error('TodoItem is required');
  }
  return todo;
}

// -----------------------------------------------------------------------------
// 6. Global ArkUI builders and reusable modifier chains
// -----------------------------------------------------------------------------

@Styles
function globalCardStyle() {
  .width('100%')
  .padding(12)
  .borderRadius(12)
  .backgroundColor(Color.White)
}

@Extend(Text)
function headline(color: ResourceColor = Color.Black) {
  .fontSize(22)
  .fontWeight(FontWeight.Bold)
  .fontColor(color)
}

@Builder
function GlobalSectionTitle(title: string, count: number = 0) {
  Row({ space: 8 }) {
    Text(title)
      .headline(Color.Blue)
    Text(`(${count})`)
      .fontSize(14)
      .fontColor(Color.Gray)
  }
  .width('100%')
}

export class BuilderState {
  label: string = '';
}

@Builder
function EditableLabel($$: BuilderState) {
  TextInput({ text: $$.label })
    .onChange((value: string) => {
      $$.label = value;
    })
}

// -----------------------------------------------------------------------------
// 7. Child components and state-management decorators
// -----------------------------------------------------------------------------

@Reusable
@Component
struct TodoRow {
  @Require
  @Prop title: string;

  @Prop index: number = 0;
  @Link selectedId: string;
  @ObjectLink item: TodoItem;

  @BuilderParam actions: () => void = this.defaultActions;

  @Builder
  defaultActions() {
    Text('No actions')
      .fontSize(12)
      .fontColor(Color.Gray)
  }

  build() {
    Row({ space: 12 }) {
      Checkbox({ name: this.item.id, group: 'todos' })
        .select(this.item.done)
        .onChange((checked: boolean) => {
          this.item.done = checked;
        })

      Column({ space: 4 }) {
        Text(`${this.index + 1}. ${this.title}`)
          .fontSize(16)
          .fontWeight(FontWeight.Medium)
        Text(this.item.describe())
          .fontSize(12)
          .fontColor(Color.Gray)
      }
      .alignItems(HorizontalAlign.Start)
      .layoutWeight(1)

      this.actions()
    }
    .globalCardStyle()
    .border({
      width: this.selectedId === this.item.id ? 2 : 1,
      color: this.selectedId === this.item.id ? Color.Blue : Color.Gray
    })
    .onClick(() => {
      this.selectedId = this.item.id;
    })
  }
}

@Component
struct CustomPanel {
  @Prop title: string = '';

  @Builder
  emptyContent() {
  }

  @BuilderParam content: () => void = this.emptyContent;

  build() {
    Column({ space: 8 }) {
      Text(this.title)
        .fontSize(18)
        .fontWeight(FontWeight.Bold)
      this.content()
    }
    .globalCardStyle()
  }
}

@Component
struct StorageCard {
  @StorageLink('arkts.allInOne.counter')
  persistedCount: number = 0;

  @StorageProp('arkts.allInOne.user')
  userName: string = 'Guest';

  @LocalStorageLink('arkts.allInOne.localFlag')
  localFlag: boolean = false;

  @LocalStorageProp('arkts.allInOne.localLabel')
  localLabel: string = 'Local';

  build() {
    Column({ space: 4 }) {
      Text(`${this.userName}: ${this.persistedCount}`)
      Text(`${this.localLabel}: ${this.localFlag}`)
    }
  }
}

@Component
struct ThemeConsumer {
  @Consume('themeColor')
  themeColor: string;

  build() {
    Text('Provided theme')
      .fontColor(this.themeColor)
  }
}

// -----------------------------------------------------------------------------
// 8. Entry component: lifecycle, state, builders, styles, control-flow UI
// -----------------------------------------------------------------------------

@Entry
@Component
struct ArkTSAllInOnePage {
  @State
  @Watch('onCountChanged')
  count: number = 0;

  @State query: string = '';
  @State loading: boolean = false;
  @State selectedId: string = 'todo-1';
  @State selectedTodo: TodoItem =
    new TodoItem('todo-1', 'Parse ArkTS', false, ['parser']);

  @Provide('themeColor')
  themeColor: string = '#007DFF';

  private readonly repository: InMemoryRepository<TodoItem> =
    new InMemoryRepository<TodoItem>();

  private todos: TodoItem[] = [
    new TodoItem('todo-1', 'Parse ArkTS', false, ['parser']),
    new TodoItem('todo-2', 'Extract Chapi models', true, ['domain']),
    new TodoItem('todo-3', 'Verify ArkUI calls', false, ['calls'])
  ];

  private clickHandler = (): void => {
    this.count++;
  };

  aboutToAppear(): void {
    console.info(`${APP_NAME} appears`);
    this.repository.save(this.selectedTodo);
  }

  aboutToDisappear(): void {
    console.info(`${APP_NAME} disappears`);
  }

  onPageShow(): void {
    bootCount++;
  }

  onPageHide(): void {
    bootCount--;
  }

  onCountChanged(propertyName: string): void {
    console.info(`${propertyName}: ${this.count}`);
  }

  private filteredTodos(): TodoItem[] {
    if (this.query.length === 0) {
      return this.todos;
    }

    return this.todos.filter((item: TodoItem): boolean =>
      item.title.toLowerCase().includes(this.query.toLowerCase())
    );
  }

  private async refresh(): Promise<void> {
    this.loading = true;
    const loaded: TodoItem | null =
      await loadTodo(this.repository, this.selectedId);
    if (loaded != null) {
      this.selectedTodo = loaded;
    }
    this.loading = false;
  }

  @Builder
  header(title: string) {
    Row({ space: 8 }) {
      Text(title)
        .headline(this.themeColor)
      Blank()
      Text(`count=${this.count}`)
        .fontSize(14)
    }
    .width('100%')
  }

  @LocalBuilder
  emptyState() {
    Column({ space: 8 }) {
      Text('No matching items')
        .fontSize(16)
      Button('Clear filter')
        .onClick(() => {
          this.query = '';
        })
    }
    .padding(16)
  }

  @Builder
  rowActions(item: TodoItem) {
    Button(item.done ? 'Undo' : 'Done')
      .onClick(() => {
        item.toggle();
        this.count++;
      })
  }

  @Styles
  pageStyle() {
    .width('100%')
    .height('100%')
    .padding(16)
    .backgroundColor(0xFFF5F5F5)
  }

  build() {
    Column({ space: 12 }) {
      this.header('ArkTS syntax fixture')
      GlobalSectionTitle('Todos', this.filteredTodos().length)
      EditableLabel({ label: this.query })

      CustomPanel({ title: 'Trailing closure' }) {
        Text('Custom component body')
          .fontSize(14)
        Text(`selected=${this.selectedId}`)
          .fontColor(Color.Gray)
      }

      TextInput({
        placeholder: 'Filter by title',
        text: this.query
      })
        .width('100%')
        .onChange((value: string) => {
          this.query = value;
        })
        .onSubmit((value: string) => {
          console.info(`submit=${value}`);
        })

      if (this.loading) {
        LoadingProgress()
          .width(32)
          .height(32)
      } else if (this.filteredTodos().length === 0) {
        this.emptyState()
      } else {
        Scroll() {
          Column({ space: 8 }) {
            ForEach(
              this.filteredTodos(),
              (item: TodoItem, index: number) => {
                TodoRow({
                  title: item.title,
                  index: index,
                  selectedId: $selectedId,
                  item: item,
                  actions: (): void => {
                    this.rowActions(item);
                  }
                })
              },
              (item: TodoItem): string => item.id
            )
          }
        }
        .layoutWeight(1)
        .scrollBar(BarState.Auto)
      }

      Divider()

      Row({ space: 8 }) {
        Button('Increment')
          .onClick(this.clickHandler)

        Button('Refresh')
          .enabled(!this.loading)
          .onClick(async (): Promise<void> => {
            await this.refresh();
          })

        Toggle({ type: ToggleType.Switch, isOn: this.selectedTodo.done })
          .onChange((isOn: boolean) => {
            this.selectedTodo.done = isOn;
          })
      }

      ThemeConsumer()
      StorageCard()

      Text($r('app.string.app_name'))
        .fontSize(12)
        .fontColor(Color.Gray)
    }
    .pageStyle()
  }
}
