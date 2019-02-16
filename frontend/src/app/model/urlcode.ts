export class URLCode {
  id: string;
  code: string;
  url: string;
  hitCount: number;

  constructor(url: string, code?: string, hitCount?: number) {
    this.url = url;
    this.code = code;
    this.hitCount = hitCount;
  }
}
