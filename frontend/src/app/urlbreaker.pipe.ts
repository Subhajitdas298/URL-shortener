import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'urlBreaker'
})
export class URLBreakerPipe implements PipeTransform {

  transform(value: string): string {
    const protocolPart = value.split('://')[0];
    const uriPart = value.split('://')[1];
    return protocolPart + '://' + uriPart.replace(/\//g, '/\u200B');
  }

}
