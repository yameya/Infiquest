import { InfiquestClientPage } from './app.po';

describe('infiquest-client App', () => {
  let page: InfiquestClientPage;

  beforeEach(() => {
    page = new InfiquestClientPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
