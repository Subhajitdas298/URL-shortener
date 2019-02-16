export class URLCode {
  id: string;
  code: string;
  url: string;
  hitCount: number;

  constructor(url: string) {
    this.url = url;
  }
}
