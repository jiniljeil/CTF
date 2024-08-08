// Decompiled with JetBrains decompiler
// Type: MVC.Pages.Contacts.Pages_Contacts_Edit
// Assembly: MVC.Views, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: 7837EB8D-71E8-4B9C-97FF-9247DC8E94C2
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.Views.dll

using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Html;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Razor;
using Microsoft.AspNetCore.Mvc.Razor.Internal;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.AspNetCore.Mvc.TagHelpers;
using Microsoft.AspNetCore.Mvc.ViewFeatures;
using Microsoft.AspNetCore.Razor.Hosting;
using Microsoft.AspNetCore.Razor.Runtime.TagHelpers;
using Microsoft.AspNetCore.Razor.TagHelpers;
using System;
using System.Text.Encodings.Web;
using System.Threading.Tasks;

#nullable disable
namespace MVC.Pages.Contacts
{
  [RazorCompiledItemMetadata("RouteTemplate", "{id:int}")]
  [RazorSourceChecksum("SHA1", "3aef3147d9e9f4309fe5f837b088405af1a67994", "/Pages/Contacts/Edit.cshtml")]
  [RazorSourceChecksum("SHA1", "908c75dc3dcf7b54343c3ceaf8f93746d238aedc", "/Pages/_ViewImports.cshtml")]
  public class Pages_Contacts_Edit : Page
  {
    private static readonly TagHelperAttribute __tagHelperAttribute_0 = new TagHelperAttribute("class", (object) new HtmlString("text-danger"), (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_1 = new TagHelperAttribute("type", (object) "hidden", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_2 = new TagHelperAttribute("class", (object) new HtmlString("control-label"), (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_3 = new TagHelperAttribute("class", (object) new HtmlString("form-control"), (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_4 = new TagHelperAttribute("method", (object) "post", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_5 = new TagHelperAttribute("asp-page", (object) "./Index", (HtmlAttributeValueStyle) 0);
    private string __tagHelperStringValueBuffer;
    private TagHelperExecutionContext __tagHelperExecutionContext;
    private TagHelperRunner __tagHelperRunner = new TagHelperRunner();
    private TagHelperScopeManager __backed__tagHelperScopeManager;
    private FormTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper;
    private RenderAtEndOfFormTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper;
    private ValidationSummaryTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_ValidationSummaryTagHelper;
    private InputTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_InputTagHelper;
    private LabelTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_LabelTagHelper;
    private ValidationMessageTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_ValidationMessageTagHelper;
    private AnchorTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper;

    private TagHelperScopeManager __tagHelperScopeManager
    {
      get
      {
        if (this.__backed__tagHelperScopeManager == null)
          this.__backed__tagHelperScopeManager = new TagHelperScopeManager(new Action<HtmlEncoder>(((RazorPageBase) this).StartTagHelperWritingScope), new Func<TagHelperContent>(((RazorPageBase) this).EndTagHelperWritingScope));
        return this.__backed__tagHelperScopeManager;
      }
    }

    public virtual async Task ExecuteAsync()
    {
      Pages_Contacts_Edit pagesContactsEdit = this;
      ((RazorPageBase) pagesContactsEdit).BeginContext(53, 1, true);
      ((RazorPageBase) pagesContactsEdit).WriteLiteral("\n");
      ((RazorPageBase) pagesContactsEdit).EndContext();
      ((ViewDataDictionary) pagesContactsEdit.ViewData)["Title"] = (object) "Edit";
      ((RazorPageBase) pagesContactsEdit).BeginContext(91, 93, true);
      ((RazorPageBase) pagesContactsEdit).WriteLiteral("\n<h2>Edit</h2>\n\n<h4>Contact</h4>\n<hr />\n<div class=\"row\">\n    <div class=\"col-md-4\">\n        ");
      ((RazorPageBase) pagesContactsEdit).EndContext();
      ((RazorPageBase) pagesContactsEdit).BeginContext(184, 2046, false);
      // ISSUE: reference to a compiler-generated method
      pagesContactsEdit.__tagHelperExecutionContext = pagesContactsEdit.__tagHelperScopeManager.Begin("form", (TagMode) 0, "3aef3147d9e9f4309fe5f837b088405af1a679946612", new Func<Task>(pagesContactsEdit.\u003CExecuteAsync\u003Eb__19_0));
      pagesContactsEdit.__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper = ((RazorPageBase) pagesContactsEdit).CreateTagHelper<FormTagHelper>();
      pagesContactsEdit.__tagHelperExecutionContext.Add((ITagHelper) pagesContactsEdit.__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper);
      pagesContactsEdit.__Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper = ((RazorPageBase) pagesContactsEdit).CreateTagHelper<RenderAtEndOfFormTagHelper>();
      pagesContactsEdit.__tagHelperExecutionContext.Add((ITagHelper) pagesContactsEdit.__Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper);
      pagesContactsEdit.__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper.Method = (string) Pages_Contacts_Edit.__tagHelperAttribute_4.Value;
      pagesContactsEdit.__tagHelperExecutionContext.AddTagHelperAttribute(Pages_Contacts_Edit.__tagHelperAttribute_4);
      await pagesContactsEdit.__tagHelperRunner.RunAsync(pagesContactsEdit.__tagHelperExecutionContext);
      if (!pagesContactsEdit.__tagHelperExecutionContext.Output.IsContentModified)
        await pagesContactsEdit.__tagHelperExecutionContext.SetOutputContentAsync();
      ((RazorPageBase) pagesContactsEdit).Write((object) pagesContactsEdit.__tagHelperExecutionContext.Output);
      pagesContactsEdit.__tagHelperExecutionContext = pagesContactsEdit.__tagHelperScopeManager.End();
      ((RazorPageBase) pagesContactsEdit).EndContext();
      ((RazorPageBase) pagesContactsEdit).BeginContext(2230, 30, true);
      ((RazorPageBase) pagesContactsEdit).WriteLiteral("\n    </div>\n</div>\n\n<div>\n    ");
      ((RazorPageBase) pagesContactsEdit).EndContext();
      ((RazorPageBase) pagesContactsEdit).BeginContext(2260, 38, false);
      // ISSUE: reference to a compiler-generated method
      pagesContactsEdit.__tagHelperExecutionContext = pagesContactsEdit.__tagHelperScopeManager.Begin("a", (TagMode) 0, "3aef3147d9e9f4309fe5f837b088405af1a6799442278", new Func<Task>(pagesContactsEdit.\u003CExecuteAsync\u003Eb__19_1));
      pagesContactsEdit.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper = ((RazorPageBase) pagesContactsEdit).CreateTagHelper<AnchorTagHelper>();
      pagesContactsEdit.__tagHelperExecutionContext.Add((ITagHelper) pagesContactsEdit.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper);
      pagesContactsEdit.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper.Page = (string) Pages_Contacts_Edit.__tagHelperAttribute_5.Value;
      pagesContactsEdit.__tagHelperExecutionContext.AddTagHelperAttribute(Pages_Contacts_Edit.__tagHelperAttribute_5);
      await pagesContactsEdit.__tagHelperRunner.RunAsync(pagesContactsEdit.__tagHelperExecutionContext);
      if (!pagesContactsEdit.__tagHelperExecutionContext.Output.IsContentModified)
        await pagesContactsEdit.__tagHelperExecutionContext.SetOutputContentAsync();
      ((RazorPageBase) pagesContactsEdit).Write((object) pagesContactsEdit.__tagHelperExecutionContext.Output);
      pagesContactsEdit.__tagHelperExecutionContext = pagesContactsEdit.__tagHelperScopeManager.End();
      ((RazorPageBase) pagesContactsEdit).EndContext();
      ((RazorPageBase) pagesContactsEdit).BeginContext(2298, 9, true);
      ((RazorPageBase) pagesContactsEdit).WriteLiteral("\n</div>\n\n");
      ((RazorPageBase) pagesContactsEdit).EndContext();
      // ISSUE: method pointer
      ((RazorPageBase) pagesContactsEdit).DefineSection("Scripts", new RenderAsyncDelegate((object) pagesContactsEdit, __methodptr(\u003CExecuteAsync\u003Eb__19_2)));
    }

    [RazorInject]
    public IAuthorizationService AuthorizationService { get; private set; }

    [RazorInject]
    public IModelExpressionProvider ModelExpressionProvider { get; private set; }

    [RazorInject]
    public IUrlHelper Url { get; private set; }

    [RazorInject]
    public IViewComponentHelper Component { get; private set; }

    [RazorInject]
    public IJsonHelper Json { get; private set; }

    [RazorInject]
    public IHtmlHelper<EditModel> Html { get; private set; }

    public ViewDataDictionary<EditModel> ViewData
    {
      get => (ViewDataDictionary<EditModel>) ((PageBase) this).PageContext?.ViewData;
    }

    public EditModel Model => this.ViewData.Model;
  }
}
