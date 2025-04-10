import { Directive, input } from '@angular/core';

interface TableRowTemplateContext<Titem extends object> {
  $implicit: Titem;
}

@Directive({
  selector: 'ng-template[appTableRow]',
  standalone: true,
})
export class TableRowDirective<Titem extends object> {
  appTableRow = input<Titem[]>();

  static ngTemplateContextGuard<TContextItem extends object>(
    dir: TableRowDirective<TContextItem>,
    ctx: unknown
  ): ctx is TableRowTemplateContext<TContextItem> {
    return true;
  }
}
