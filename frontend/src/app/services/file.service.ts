import { HttpClient, HttpParams } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import {
  BehaviorSubject,
  combineLatest,
  map,
  Observable,
  switchMap,
} from 'rxjs';
import { environment } from '../../environments/environment';
import { FilesResponse, GetFiles } from '../models/responses';


@Injectable({
  providedIn: 'root',
})
export class FileService {
  private http = inject(HttpClient);
  private url = environment.apiUrl;
  private _page = new BehaviorSubject<number>(0);
  private _size = new BehaviorSubject<number>(5);

  page$ = this._page.asObservable();
  size$ = this._size.asObservable();

  private getFiles(page: number, size: number): Observable<FilesResponse> {
    const params = new HttpParams().set('page', page).set('size', size);
    return this.http.get<GetFiles>(`${this.url}/file`, { params }).pipe(
      map(({ data }) => {
        const { content: files, ...pageData } = data;

        return {
          files,
          pageData,
        };
      })
    );
  }

  files$ = combineLatest([this.page$, this.size$]).pipe(
    switchMap(([page, size]) => this.getFiles(page, size))
  );

  setPage(idx: number) {
    const page = idx / this._size.getValue();
    this._page.next(page);
  }

  setSize(size: number) {
    this._page.next(0);
    this._size.next(size);
  }

  get size() {
    return this._size.getValue();
  }

  trigger() {
    this._page.next(this._page.getValue());
  }
}
