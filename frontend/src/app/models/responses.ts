export interface UploadFileResponse {
  status: string;
  data: {
    url: string;
  };
  message: string;
}

export interface GetFiles {
  status: string;
  data: FilePage;
}

export interface FilePage extends PageData {
  content: File[];
}

export interface PageData {
  number: number;
  size: number;
  totalElements: number;
  totalPages: number;
  first: boolean;
  last: boolean;
}

export interface File {
  id: string;
  originalFilename: string;
  url: string;
  contentType: string;
}

export interface FilesResponse {
  files: File[];
  pageData: PageData;
}
