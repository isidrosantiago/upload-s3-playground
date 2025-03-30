import { HttpResponse } from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';
import { AsyncPipe } from '@angular/common';
import { MessageService } from 'primeng/api';
import { Toast } from 'primeng/toast';
import { FileUpload, FileUploadEvent } from 'primeng/fileupload';
import { TableLazyLoadEvent, TableModule } from 'primeng/table';
import { Button } from 'primeng/button';
import { Image } from 'primeng/image';
import { TableRowDirective } from './directives/table-row.directive';
import { FileService } from './services/file.service';
import { File, UploadFileResponse } from './models/responses';

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  imports: [
    Toast,
    FileUpload,
    TableModule,
    AsyncPipe,
    TableRowDirective,
    Button,
    Image,
  ],
  providers: [MessageService],
})
export class AppComponent implements OnInit {
  private messageService = inject(MessageService);
  fileService = inject(FileService);

  file: File | null = null;

  ngOnInit(): void {}

  onUpload(event: FileUploadEvent) {
    const detail = event.originalEvent as HttpResponse<UploadFileResponse>;
    this.messageService.add({
      severity: 'success',
      summary: 'Success',
      detail: detail.body?.message,
      life: 3000,
    });

    this.fileService.trigger();
  }

  changePage(event: TableLazyLoadEvent) {
    const first = event.first as number;
    const rows = event.rows as number;

    if (rows !== this.fileService.size) {
      this.fileService.setSize(rows);
    } else {
      this.fileService.setPage(first);
    }
  }

  viewFile(file: File) {
    this.file = file;
  }

  get url(): string {
    return this.file ? this.file.url : '';
  }
}
