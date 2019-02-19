import { URLBreakerPipe } from './urlbreaker.pipe';

describe('URLBreakerPipe', () => {
  it('create an instance', () => {
    const pipe = new URLBreakerPipe();
    expect(pipe).toBeTruthy();
  });
});
