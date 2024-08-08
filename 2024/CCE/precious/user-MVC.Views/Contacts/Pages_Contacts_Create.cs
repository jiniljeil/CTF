// Decompiled with JetBrains decompiler
// Type: MVC.Pages.Contacts.Pages_Contacts_Create
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
  [RazorSourceChecksum("SHA1", "dfd707e028e94889f05c8251037cff17642b8709", "/Pages/Contacts/Create.cshtml")]
  [RazorSourceChecksum("SHA1", "908c75dc3dcf7b54343c3ceaf8f93746d238aedc", "/Pages/_ViewImports.cshtml")]
  public class Pages_Contacts_Create : Page
  {
    private static readonly TagHelperAttribute __tagHelperAttribute_0 = new TagHelperAttribute("class", (object) new HtmlString("text-danger"), (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_1 = new TagHelperAttribute("class", (object) new HtmlString("control-label"), (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_2 = new TagHelperAttribute("class", (object) new HtmlString("form-control"), (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_3 = new TagHelperAttribute("method", (object) "post", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_4 = new TagHelperAttribute("asp-page", (object) "Index", (HtmlAttributeValueStyle) 0);
    private string __tagHelperStringValueBuffer;
    private TagHelperExecutionContext __tagHelperExecutionContext;
    private TagHelperRunner __tagHelperRunner = new TagHelperRunner();
    private TagHelperScopeManager __backed__tagHelperScopeManager;
    private FormTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper;
    private RenderAtEndOfFormTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper;
    private ValidationSummaryTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_ValidationSummaryTagHelper;
    private LabelTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_LabelTagHelper;
    private InputTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_InputTagHelper;
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
      Pages_Contacts_Create pagesContactsCreate = this;
      ((RazorPageBase) pagesContactsCreate).BeginContext(44, 1, true);
      ((RazorPageBase) pagesContactsCreate).WriteLiteral("\n");
      ((RazorPageBase) pagesContactsCreate).EndContext();
      ((ViewDataDictionary) pagesContactsCreate.ViewData)["Title"] = (object) "Create";
      ((RazorPageBase) pagesContactsCreate).BeginContext(84, 95, true);
      ((RazorPageBase) pagesContactsCreate).WriteLiteral("\n<h2>Create</h2>\n\n<h4>Contact</h4>\n<hr />\n<div class=\"row\">\n    <div class=\"col-md-4\">\n        ");
      ((RazorPageBase) pagesContactsCreate).EndContext();
      ((RazorPageBase) pagesContactsCreate).BeginContext(179, 1984, false);
      // ISSUE: reference to a compiler-generated method
      pagesContactsCreate.__tagHelperExecutionContext = pagesContactsCreate.__tagHelperScopeManager.Begin("form", (TagMode) 0, "dfd707e028e94889f05c8251037cff17642b87096216", new Func<Task>(pagesContactsCreate.\u003CExecuteAsync\u003Eb__18_0));
      pagesContactsCreate.__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper = ((RazorPageBase) pagesContactsCreate).CreateTagHelper<FormTagHelper>();
      pagesContactsCreate.__tagHelperExecutionContext.Add((ITagHelper) pagesContactsCreate.__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper);
      pagesContactsCreate.__Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper = ((RazorPageBase) pagesContactsCreate).CreateTagHelper<RenderAtEndOfFormTagHelper>();
      pagesContactsCreate.__tagHelperExecutionContext.Add((ITagHelper) pagesContactsCreate.__Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper);
      pagesContactsCreate.__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper.Method = (string) Pages_Contacts_Create.__tagHelperAttribute_3.Value;
      pagesContactsCreate.__tagHelperExecutionContext.AddTagHelperAttribute(Pages_Contacts_Create.__tagHelperAttribute_3);
      await pagesContactsCreate.__tagHelperRunner.RunAsync(pagesContactsCreate.__tagHelperExecutionContext);
      if (!pagesContactsCreate.__tagHelperExecutionContext.Output.IsContentModified)
        await pagesContactsCreate.__tagHelperExecutionContext.SetOutputContentAsync();
      ((RazorPageBase) pagesContactsCreate).Write((object) pagesContactsCreate.__tagHelperExecutionContext.Output);
      pagesContactsCreate.__tagHelperExecutionContext = pagesContactsCreate.__tagHelperScopeManager.End();
      ((RazorPageBase) pagesContactsCreate).EndContext();
      ((RazorPageBase) pagesContactsCreate).BeginContext(2163, 30, true);
      ((RazorPageBase) pagesContactsCreate).WriteLiteral("\n    </div>\n</div>\n\n<div>\n    ");
      ((RazorPageBase) pagesContactsCreate).EndContext();
      ((RazorPageBase) pagesContactsCreate).BeginContext(2193, 36, false);
      // ISSUE: reference to a compiler-generated method
      pagesContactsCreate.__tagHelperExecutionContext = pagesContactsCreate.__tagHelperScopeManager.Begin("a", (TagMode) 0, "dfd707e028e94889f05c8251037cff17642b870940153", new Func<Task>(pagesContactsCreate.\u003CExecuteAsync\u003Eb__18_1));
      pagesContactsCreate.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper = ((RazorPageBase) pagesContactsCreate).CreateTagHelper<AnchorTagHelper>();
      pagesContactsCreate.__tagHelperExecutionContext.Add((ITagHelper) pagesContactsCreate.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper);
      pagesContactsCreate.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper.Page = (string) Pages_Contacts_Create.__tagHelperAttribute_4.Value;
      pagesContactsCreate.__tagHelperExecutionContext.AddTagHelperAttribute(Pages_Contacts_Create.__tagHelperAttribute_4);
      await pagesContactsCreate.__tagHelperRunner.RunAsync(pagesContactsCreate.__tagHelperExecutionContext);
      if (!pagesContactsCreate.__tagHelperExecutionContext.Output.IsContentModified)
        await pagesContactsCreate.__tagHelperExecutionContext.SetOutputContentAsync();
      ((RazorPageBase) pagesContactsCreate).Write((object) pagesContactsCreate.__tagHelperExecutionContext.Output);
      pagesContactsCreate.__tagHelperExecutionContext = pagesContactsCreate.__tagHelperScopeManager.End();
      ((RazorPageBase) pagesContactsCreate).EndContext();
      ((RazorPageBase) pagesContactsCreate).BeginContext(2229, 9, true);
      ((RazorPageBase) pagesContactsCreate).WriteLiteral("\n</div>\n\n");
      ((RazorPageBase) pagesContactsCreate).EndContext();
      // ISSUE: method pointer
      ((RazorPageBase) pagesContactsCreate).DefineSection("Scripts", new RenderAsyncDelegate((object) pagesContactsCreate, __methodptr(\u003CExecuteAsync\u003Eb__18_2)));
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
    public IHtmlHelper<CreateModel> Html { get; private set; }

    public ViewDataDictionary<CreateModel> ViewData
    {
      get => (ViewDataDictionary<CreateModel>) ((PageBase) this).PageContext?.ViewData;
    }

    public CreateModel Model => this.ViewData.Model;
  }
}
