import { URLCode } from './urlcode';

describe('URLCode', () => {
  let urlCode: URLCode;

  afterAll(() => {
    urlCode = new URLCode('');
  });

  it('should create an instance', () => {
    expect(new URLCode('')).toBeTruthy();
  });
});
