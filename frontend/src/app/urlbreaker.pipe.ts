import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'urlBreaker'
})
export class URLBreakerPipe implements PipeTransform {

  transform(value: string): string {
    const parts = value.split('://');
    let url: string;
    if (parts[1] !== undefined) {
      url = parts[0] + '://' + parts[1].replace(/\//g, '/\u200B');
    } else {
      url =  parts[0].replace(/\//g, '/\u200B');
    }
    return url;
  }

}
