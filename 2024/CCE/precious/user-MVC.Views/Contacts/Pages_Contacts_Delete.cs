// Decompiled with JetBrains decompiler
// Type: MVC.Pages.Contacts.Pages_Contacts_Delete
// Assembly: MVC.Views, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: 7837EB8D-71E8-4B9C-97FF-9247DC8E94C2
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.Views.dll

using Microsoft.AspNetCore.Authorization;
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
using System.Linq.Expressions;
using System.Text.Encodings.Web;
using System.Threading.Tasks;

#nullable disable
namespace MVC.Pages.Contacts
{
  [RazorCompiledItemMetadata("RouteTemplate", "{id:int}")]
  [RazorSourceChecksum("SHA1", "0d433845e9bd4804effc80001175bd2e4b1b816e", "/Pages/Contacts/Delete.cshtml")]
  [RazorSourceChecksum("SHA1", "908c75dc3dcf7b54343c3ceaf8f93746d238aedc", "/Pages/_ViewImports.cshtml")]
  public class Pages_Contacts_Delete : Page
  {
    private static readonly TagHelperAttribute __tagHelperAttribute_0 = new TagHelperAttribute("type", (object) "hidden", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_1 = new TagHelperAttribute("asp-page", (object) "./Index", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_2 = new TagHelperAttribute("method", (object) "post", (HtmlAttributeValueStyle) 0);
    private string __tagHelperStringValueBuffer;
    private TagHelperExecutionContext __tagHelperExecutionContext;
    private TagHelperRunner __tagHelperRunner = new TagHelperRunner();
    private TagHelperScopeManager __backed__tagHelperScopeManager;
    private FormTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper;
    private RenderAtEndOfFormTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper;
    private InputTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_InputTagHelper;
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
      Pages_Contacts_Delete pagesContactsDelete = this;
      ((RazorPageBase) pagesContactsDelete).BeginContext(55, 1, true);
      ((RazorPageBase) pagesContactsDelete).WriteLiteral("\n");
      ((RazorPageBase) pagesContactsDelete).EndContext();
      ((ViewDataDictionary) pagesContactsDelete.ViewData)["Title"] = (object) "Delete";
      ((RazorPageBase) pagesContactsDelete).BeginContext(95, 159, true);
      ((RazorPageBase) pagesContactsDelete).WriteLiteral("\n<h2>Delete</h2>\n\n<h3>Are you sure you want to delete this?</h3>\n<div>\n    <h4>Contact</h4>\n    <hr />\n    <dl class=\"dl-horizontal\">\n        <dt>\n            ");
      ((RazorPageBase) pagesContactsDelete).EndContext();
      ((RazorPageBase) pagesContactsDelete).BeginContext((int) byte.MaxValue, 48, false);
      ((RazorPageBase) pagesContactsDelete).Write(pagesContactsDelete.Html.DisplayNameFor<string>((Expression<Func<DeleteModel, string>>) (model => model.Contact.Name)));
      ((RazorPageBase) pagesContactsDelete).EndContext();
      ((RazorPageBase) pagesContactsDelete).BeginContext(303, 40, true);
      ((RazorPageBase) pagesContactsDelete).WriteLiteral("\n        </dt>\n        <dd>\n            ");
      ((RazorPageBase) pagesContactsDelete).EndContext();
      ((RazorPageBase) pagesContactsDelete).BeginContext(344, 44, false);
      ((RazorPageBase) pagesContactsDelete).Write((object) HtmlHelperDisplayExtensions.DisplayFor<DeleteModel, string>(pagesContactsDelete.Html, (Expression<Func<DeleteModel, string>>) (model => model.Contact.Name)));
      ((RazorPageBase) pagesContactsDelete).EndContext();
      ((RazorPageBase) pagesContactsDelete).BeginContext(388, 40, true);
      ((RazorPageBase) pagesContactsDelete).WriteLiteral("\n        </dd>\n        <dt>\n            ");
      ((RazorPageBase) pagesContactsDelete).EndContext();
      ((RazorPageBase) pagesContactsDelete).BeginContext(429, 51, false);
      ((RazorPageBase) pagesContactsDelete).Write(pagesContactsDelete.Html.DisplayNameFor<string>((Expression<Func<DeleteModel, string>>) (model => model.Contact.Address)));
      ((RazorPageBase) pagesContactsDelete).EndContext();
      ((RazorPageBase) pagesContactsDelete).BeginContext(480, 40, true);
      ((RazorPageBase) pagesContactsDelete).WriteLiteral("\n        </dt>\n        <dd>\n            ");
      ((RazorPageBase) pagesContactsDelete).EndContext();
      ((RazorPageBase) pagesContactsDelete).BeginContext(521, 47, false);
      ((RazorPageBase) pagesContactsDelete).Write((object) HtmlHelperDisplayExtensions.DisplayFor<DeleteModel, string>(pagesContactsDelete.Html, (Expression<Func<DeleteModel, string>>) (model => model.Contact.Address)));
      ((RazorPageBase) pagesContactsDelete).EndContext();
      ((RazorPageBase) pagesContactsDelete).BeginContext(568, 40, true);
      ((RazorPageBase) pagesContactsDelete).WriteLiteral("\n        </dd>\n        <dt>\n            ");
      ((RazorPageBase) pagesContactsDelete).EndContext();
      ((RazorPageBase) pagesContactsDelete).BeginContext(609, 48, false);
      ((RazorPageBase) pagesContactsDelete).Write(pagesContactsDelete.Html.DisplayNameFor<string>((Expression<Func<DeleteModel, string>>) (model => model.Contact.City)));
      ((RazorPageBase) pagesContactsDelete).EndContext();
      ((RazorPageBase) pagesContactsDelete).BeginContext(657, 40, true);
      ((RazorPageBase) pagesContactsDelete).WriteLiteral("\n        </dt>\n        <dd>\n            ");
      ((RazorPageBase) pagesContactsDelete).EndContext();
      ((RazorPageBase) pagesContactsDelete).BeginContext(698, 44, false);
      ((RazorPageBase) pagesContactsDelete).Write((object) HtmlHelperDisplayExtensions.DisplayFor<DeleteModel, string>(pagesContactsDelete.Html, (Expression<Func<DeleteModel, string>>) (model => model.Contact.City)));
      ((RazorPageBase) pagesContactsDelete).EndContext();
      ((RazorPageBase) pagesContactsDelete).BeginContext(742, 40, true);
      ((RazorPageBase) pagesContactsDelete).WriteLiteral("\n        </dd>\n        <dt>\n            ");
      ((RazorPageBase) pagesContactsDelete).EndContext();
      ((RazorPageBase) pagesContactsDelete).BeginContext(783, 49, false);
      ((RazorPageBase) pagesContactsDelete).Write(pagesContactsDelete.Html.DisplayNameFor<string>((Expression<Func<DeleteModel, string>>) (model => model.Contact.State)));
      ((RazorPageBase) pagesContactsDelete).EndContext();
      ((RazorPageBase) pagesContactsDelete).BeginContext(832, 40, true);
      ((RazorPageBase) pagesContactsDelete).WriteLiteral("\n        </dt>\n        <dd>\n            ");
      ((RazorPageBase) pagesContactsDelete).EndContext();
      ((RazorPageBase) pagesContactsDelete).BeginContext(873, 45, false);
      ((RazorPageBase) pagesContactsDelete).Write((object) HtmlHelperDisplayExtensions.DisplayFor<DeleteModel, string>(pagesContactsDelete.Html, (Expression<Func<DeleteModel, string>>) (model => model.Contact.State)));
      ((RazorPageBase) pagesContactsDelete).EndContext();
      ((RazorPageBase) pagesContactsDelete).BeginContext(918, 40, true);
      ((RazorPageBase) pagesContactsDelete).WriteLiteral("\n        </dd>\n        <dt>\n            ");
      ((RazorPageBase) pagesContactsDelete).EndContext();
      ((RazorPageBase) pagesContactsDelete).BeginContext(959, 47, false);
      ((RazorPageBase) pagesContactsDelete).Write(pagesContactsDelete.Html.DisplayNameFor<string>((Expression<Func<DeleteModel, string>>) (model => model.Contact.Zip)));
      ((RazorPageBase) pagesContactsDelete).EndContext();
      ((RazorPageBase) pagesContactsDelete).BeginContext(1006, 40, true);
      ((RazorPageBase) pagesContactsDelete).WriteLiteral("\n        </dt>\n        <dd>\n            ");
      ((RazorPageBase) pagesContactsDelete).EndContext();
      ((RazorPageBase) pagesContactsDelete).BeginContext(1047, 43, false);
      ((RazorPageBase) pagesContactsDelete).Write((object) HtmlHelperDisplayExtensions.DisplayFor<DeleteModel, string>(pagesContactsDelete.Html, (Expression<Func<DeleteModel, string>>) (model => model.Contact.Zip)));
      ((RazorPageBase) pagesContactsDelete).EndContext();
      ((RazorPageBase) pagesContactsDelete).BeginContext(1090, 40, true);
      ((RazorPageBase) pagesContactsDelete).WriteLiteral("\n        </dd>\n        <dt>\n            ");
      ((RazorPageBase) pagesContactsDelete).EndContext();
      ((RazorPageBase) pagesContactsDelete).BeginContext(1131, 49, false);
      ((RazorPageBase) pagesContactsDelete).Write(pagesContactsDelete.Html.DisplayNameFor<string>((Expression<Func<DeleteModel, string>>) (model => model.Contact.Email)));
      ((RazorPageBase) pagesContactsDelete).EndContext();
      ((RazorPageBase) pagesContactsDelete).BeginContext(1180, 40, true);
      ((RazorPageBase) pagesContactsDelete).WriteLiteral("\n        </dt>\n        <dd>\n            ");
      ((RazorPageBase) pagesContactsDelete).EndContext();
      ((RazorPageBase) pagesContactsDelete).BeginContext(1221, 45, false);
      ((RazorPageBase) pagesContactsDelete).Write((object) HtmlHelperDisplayExtensions.DisplayFor<DeleteModel, string>(pagesContactsDelete.Html, (Expression<Func<DeleteModel, string>>) (model => model.Contact.Email)));
      ((RazorPageBase) pagesContactsDelete).EndContext();
      ((RazorPageBase) pagesContactsDelete).BeginContext(1266, 30, true);
      ((RazorPageBase) pagesContactsDelete).WriteLiteral("\n        </dd>\n    </dl>\n\n    ");
      ((RazorPageBase) pagesContactsDelete).EndContext();
      ((RazorPageBase) pagesContactsDelete).BeginContext(1296, 212, false);
      // ISSUE: reference to a compiler-generated method
      pagesContactsDelete.__tagHelperExecutionContext = pagesContactsDelete.__tagHelperScopeManager.Begin("form", (TagMode) 0, "0d433845e9bd4804effc80001175bd2e4b1b816e9331", new Func<Task>(pagesContactsDelete.\u003CExecuteAsync\u003Eb__13_12));
      pagesContactsDelete.__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper = ((RazorPageBase) pagesContactsDelete).CreateTagHelper<FormTagHelper>();
      pagesContactsDelete.__tagHelperExecutionContext.Add((ITagHelper) pagesContactsDelete.__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper);
      pagesContactsDelete.__Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper = ((RazorPageBase) pagesContactsDelete).CreateTagHelper<RenderAtEndOfFormTagHelper>();
      pagesContactsDelete.__tagHelperExecutionContext.Add((ITagHelper) pagesContactsDelete.__Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper);
      pagesContactsDelete.__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper.Method = (string) Pages_Contacts_Delete.__tagHelperAttribute_2.Value;
      pagesContactsDelete.__tagHelperExecutionContext.AddTagHelperAttribute(Pages_Contacts_Delete.__tagHelperAttribute_2);
      await pagesContactsDelete.__tagHelperRunner.RunAsync(pagesContactsDelete.__tagHelperExecutionContext);
      if (!pagesContactsDelete.__tagHelperExecutionContext.Output.IsContentModified)
        await pagesContactsDelete.__tagHelperExecutionContext.SetOutputContentAsync();
      ((RazorPageBase) pagesContactsDelete).Write((object) pagesContactsDelete.__tagHelperExecutionContext.Output);
      pagesContactsDelete.__tagHelperExecutionContext = pagesContactsDelete.__tagHelperScopeManager.End();
      ((RazorPageBase) pagesContactsDelete).EndContext();
      ((RazorPageBase) pagesContactsDelete).BeginContext(1508, 8, true);
      ((RazorPageBase) pagesContactsDelete).WriteLiteral("\n</div>\n");
      ((RazorPageBase) pagesContactsDelete).EndContext();
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
    public IHtmlHelper<DeleteModel> Html { get; private set; }

    public ViewDataDictionary<DeleteModel> ViewData
    {
      get => (ViewDataDictionary<DeleteModel>) ((PageBase) this).PageContext?.ViewData;
    }

    public DeleteModel Model => this.ViewData.Model;
  }
}
