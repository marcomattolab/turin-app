import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { EmployeeComponentsPage, EmployeeDeleteDialog, EmployeeUpdatePage } from './employee.page-object';
import * as path from 'path';

const expect = chai.expect;

describe('Employee e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let employeeComponentsPage: EmployeeComponentsPage;
  let employeeUpdatePage: EmployeeUpdatePage;
  let employeeDeleteDialog: EmployeeDeleteDialog;
  const fileNameToUpload = 'logo-jhipster.png';
  const fileToUpload = '../../../../../../src/main/webapp/content/images/' + fileNameToUpload;
  const absolutePath = path.resolve(__dirname, fileToUpload);

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Employees', async () => {
    await navBarPage.goToEntity('employee');
    employeeComponentsPage = new EmployeeComponentsPage();
    await browser.wait(ec.visibilityOf(employeeComponentsPage.title), 5000);
    expect(await employeeComponentsPage.getTitle()).to.eq('turinApp.employee.home.title');
    await browser.wait(ec.or(ec.visibilityOf(employeeComponentsPage.entities), ec.visibilityOf(employeeComponentsPage.noResult)), 1000);
  });

  it('should load create Employee page', async () => {
    await employeeComponentsPage.clickOnCreateButton();
    employeeUpdatePage = new EmployeeUpdatePage();
    expect(await employeeUpdatePage.getPageTitle()).to.eq('turinApp.employee.home.createOrEditLabel');
    await employeeUpdatePage.cancel();
  });

  it('should create and save Employees', async () => {
    const nbButtonsBeforeCreate = await employeeComponentsPage.countDeleteButtons();

    await employeeComponentsPage.clickOnCreateButton();

    await promise.all([
      employeeUpdatePage.setFirstNameInput('firstName'),
      employeeUpdatePage.setLastNameInput('lastName'),
      employeeUpdatePage.setEmailInput('email'),
      employeeUpdatePage.setPhoneNumberInput('phoneNumber'),
      employeeUpdatePage.setHireDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      employeeUpdatePage.setSalaryInput('5'),
      employeeUpdatePage.setCommissionPctInput('5'),
      employeeUpdatePage.setDocumentInput(absolutePath),
      employeeUpdatePage.managerSelectLastOption(),
      employeeUpdatePage.departmentSelectLastOption(),
    ]);

    expect(await employeeUpdatePage.getFirstNameInput()).to.eq('firstName', 'Expected FirstName value to be equals to firstName');
    expect(await employeeUpdatePage.getLastNameInput()).to.eq('lastName', 'Expected LastName value to be equals to lastName');
    expect(await employeeUpdatePage.getEmailInput()).to.eq('email', 'Expected Email value to be equals to email');
    expect(await employeeUpdatePage.getPhoneNumberInput()).to.eq('phoneNumber', 'Expected PhoneNumber value to be equals to phoneNumber');
    expect(await employeeUpdatePage.getHireDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected hireDate value to be equals to 2000-12-31'
    );
    expect(await employeeUpdatePage.getSalaryInput()).to.eq('5', 'Expected salary value to be equals to 5');
    expect(await employeeUpdatePage.getCommissionPctInput()).to.eq('5', 'Expected commissionPct value to be equals to 5');
    expect(await employeeUpdatePage.getDocumentInput()).to.endsWith(
      fileNameToUpload,
      'Expected Document value to be end with ' + fileNameToUpload
    );

    await employeeUpdatePage.save();
    expect(await employeeUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await employeeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Employee', async () => {
    const nbButtonsBeforeDelete = await employeeComponentsPage.countDeleteButtons();
    await employeeComponentsPage.clickOnLastDeleteButton();

    employeeDeleteDialog = new EmployeeDeleteDialog();
    expect(await employeeDeleteDialog.getDialogTitle()).to.eq('turinApp.employee.delete.question');
    await employeeDeleteDialog.clickOnConfirmButton();

    expect(await employeeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
