import { Directive, ElementRef, EventEmitter, HostListener, Output } from '@angular/core';

@Directive({
  selector: '[soloNumeroEntero]',
  host: { "(input)": 'onInputChange($event)' }
})
export class NumeroEnteroDirective {
  @Output() ngModelChange: EventEmitter<any> = new EventEmitter();
  value: any;
  constructor(private el: ElementRef) { }
  onInputChange($event) {
    this.value = $event.target.value;
    const regex = /[^0-9]/g;
    if (regex.test(this.value)) {
      this.el.nativeElement.value = this.value.substring(0, this.value.length - 1);
      this.ngModelChange.emit(this.value.substring(0, this.value.length - 1));
    }
  }
  @HostListener('keydown', ['$event']) onKeyDown(e: KeyboardEvent) {
    if (this.ValidacionBotones(e)) return;
    const valor: string = this.el.nativeElement.value;
    const regex = /[^0-9]/g;
    if (regex.test(this.el.nativeElement.value)) {
      this.el.nativeElement.value = valor.substring(0, valor.length - 1);
    }
  }
  @HostListener('window:keyup', ['$event']) keyEvent(e: KeyboardEvent) {
    if (this.ValidacionBotones(e)) return;
    const valor: string = this.el.nativeElement.value;
    const regex = /[^0-9]/g;
    if (regex.test(this.el.nativeElement.value)) {
      this.el.nativeElement.value = valor.substring(0, valor.length - 1);
    }
  }
  @HostListener('paste', ['$event']) onPaste(event: any) {
    event.preventDefault();
    document.execCommand('insertText', false, '');
    const pastedInput: string = event.clipboardData.getData('text/plain').replace(/[^0-9]/g, '');
    document.execCommand('insertText', false, pastedInput);
  }
  ValidacionBotones(e) {/* Permite: Delete, Backspace, Tab, Escape, Enter - Home, End, Left, Right */
    if ([46, 8, 9, 27, 13].indexOf(e.keyCode) !== -1 || (e.keyCode >= 35 && e.keyCode <= 39)) {
      return true;
    }
  }
}