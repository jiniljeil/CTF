// Decompiled with JetBrains decompiler
// Type: MVC.Pages.Contacts.Pages_Contacts_Details
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
using MVC.Authorization;
using MVC.Models;
using System;
using System.Linq.Expressions;
using System.Text.Encodings.Web;
using System.Threading.Tasks;

#nullable disable
namespace MVC.Pages.Contacts
{
  [RazorCompiledItemMetadata("RouteTemplate", "{id:int}")]
  [RazorSourceChecksum("SHA1", "9b3a5da276655e31ca2b7c43144e028ee5a1860c", "/Pages/Contacts/Details.cshtml")]
  [RazorSourceChecksum("SHA1", "908c75dc3dcf7b54343c3ceaf8f93746d238aedc", "/Pages/_ViewImports.cshtml")]
  public class Pages_Contacts_Details : Page
  {
    private static readonly TagHelperAttribute __tagHelperAttribute_0 = new TagHelperAttribute("style", (object) new HtmlString("display:inline;"), (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_1 = new TagHelperAttribute("method", (object) "post", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_2 = new TagHelperAttribute("asp-page", (object) "./Edit", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_3 = new TagHelperAttribute("asp-page", (object) "./Index", (HtmlAttributeValueStyle) 0);
    private string __tagHelperStringValueBuffer;
    private TagHelperExecutionContext __tagHelperExecutionContext;
    private TagHelperRunner __tagHelperRunner = new TagHelperRunner();
    private TagHelperScopeManager __backed__tagHelperScopeManager;
    private FormTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper;
    private RenderAtEndOfFormTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper;
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
      Pages_Contacts_Details pagesContactsDetails = this;
      ((RazorPageBase) pagesContactsDetails).BeginContext(56, 1, true);
      ((RazorPageBase) pagesContactsDetails).WriteLiteral("\n");
      ((RazorPageBase) pagesContactsDetails).EndContext();
      ((ViewDataDictionary) pagesContactsDetails.ViewData)["Title"] = (object) "Details";
      ((RazorPageBase) pagesContactsDetails).BeginContext(97, 113, true);
      ((RazorPageBase) pagesContactsDetails).WriteLiteral("\n<h2>Details</h2>\n\n<div>\n    <h4>Contact</h4>\n    <hr />\n    <dl class=\"dl-horizontal\">\n        <dt>\n            ");
      ((RazorPageBase) pagesContactsDetails).EndContext();
      ((RazorPageBase) pagesContactsDetails).BeginContext(211, 48, false);
      ((RazorPageBase) pagesContactsDetails).Write(pagesContactsDetails.Html.DisplayNameFor<string>((Expression<Func<DetailsModel, string>>) (model => model.Contact.Name)));
      ((RazorPageBase) pagesContactsDetails).EndContext();
      ((RazorPageBase) pagesContactsDetails).BeginContext(259, 40, true);
      ((RazorPageBase) pagesContactsDetails).WriteLiteral("\n        </dt>\n        <dd>\n            ");
      ((RazorPageBase) pagesContactsDetails).EndContext();
      ((RazorPageBase) pagesContactsDetails).BeginContext(300, 44, false);
      ((RazorPageBase) pagesContactsDetails).Write((object) HtmlHelperDisplayExtensions.DisplayFor<DetailsModel, string>(pagesContactsDetails.Html, (Expression<Func<DetailsModel, string>>) (model => model.Contact.Name)));
      ((RazorPageBase) pagesContactsDetails).EndContext();
      ((RazorPageBase) pagesContactsDetails).BeginContext(344, 40, true);
      ((RazorPageBase) pagesContactsDetails).WriteLiteral("\n        </dd>\n        <dt>\n            ");
      ((RazorPageBase) pagesContactsDetails).EndContext();
      ((RazorPageBase) pagesContactsDetails).BeginContext(385, 51, false);
      ((RazorPageBase) pagesContactsDetails).Write(pagesContactsDetails.Html.DisplayNameFor<string>((Expression<Func<DetailsModel, string>>) (model => model.Contact.Address)));
      ((RazorPageBase) pagesContactsDetails).EndContext();
      ((RazorPageBase) pagesContactsDetails).BeginContext(436, 40, true);
      ((RazorPageBase) pagesContactsDetails).WriteLiteral("\n        </dt>\n        <dd>\n            ");
      ((RazorPageBase) pagesContactsDetails).EndContext();
      ((RazorPageBase) pagesContactsDetails).BeginContext(477, 47, false);
      ((RazorPageBase) pagesContactsDetails).Write((object) HtmlHelperDisplayExtensions.DisplayFor<DetailsModel, string>(pagesContactsDetails.Html, (Expression<Func<DetailsModel, string>>) (model => model.Contact.Address)));
      ((RazorPageBase) pagesContactsDetails).EndContext();
      ((RazorPageBase) pagesContactsDetails).BeginContext(524, 40, true);
      ((RazorPageBase) pagesContactsDetails).WriteLiteral("\n        </dd>\n        <dt>\n            ");
      ((RazorPageBase) pagesContactsDetails).EndContext();
      ((RazorPageBase) pagesContactsDetails).BeginContext(565, 48, false);
      ((RazorPageBase) pagesContactsDetails).Write(pagesContactsDetails.Html.DisplayNameFor<string>((Expression<Func<DetailsModel, string>>) (model => model.Contact.City)));
      ((RazorPageBase) pagesContactsDetails).EndContext();
      ((RazorPageBase) pagesContactsDetails).BeginContext(613, 40, true);
      ((RazorPageBase) pagesContactsDetails).WriteLiteral("\n        </dt>\n        <dd>\n            ");
      ((RazorPageBase) pagesContactsDetails).EndContext();
      ((RazorPageBase) pagesContactsDetails).BeginContext(654, 44, false);
      ((RazorPageBase) pagesContactsDetails).Write((object) HtmlHelperDisplayExtensions.DisplayFor<DetailsModel, string>(pagesContactsDetails.Html, (Expression<Func<DetailsModel, string>>) (model => model.Contact.City)));
      ((RazorPageBase) pagesContactsDetails).EndContext();
      ((RazorPageBase) pagesContactsDetails).BeginContext(698, 40, true);
      ((RazorPageBase) pagesContactsDetails).WriteLiteral("\n        </dd>\n        <dt>\n            ");
      ((RazorPageBase) pagesContactsDetails).EndContext();
      ((RazorPageBase) pagesContactsDetails).BeginContext(739, 49, false);
      ((RazorPageBase) pagesContactsDetails).Write(pagesContactsDetails.Html.DisplayNameFor<string>((Expression<Func<DetailsModel, string>>) (model => model.Contact.State)));
      ((RazorPageBase) pagesContactsDetails).EndContext();
      ((RazorPageBase) pagesContactsDetails).BeginContext(788, 40, true);
      ((RazorPageBase) pagesContactsDetails).WriteLiteral("\n        </dt>\n        <dd>\n            ");
      ((RazorPageBase) pagesContactsDetails).EndContext();
      ((RazorPageBase) pagesContactsDetails).BeginContext(829, 45, false);
      ((RazorPageBase) pagesContactsDetails).Write((object) HtmlHelperDisplayExtensions.DisplayFor<DetailsModel, string>(pagesContactsDetails.Html, (Expression<Func<DetailsModel, string>>) (model => model.Contact.State)));
      ((RazorPageBase) pagesContactsDetails).EndContext();
      ((RazorPageBase) pagesContactsDetails).BeginContext(874, 40, true);
      ((RazorPageBase) pagesContactsDetails).WriteLiteral("\n        </dd>\n        <dt>\n            ");
      ((RazorPageBase) pagesContactsDetails).EndContext();
      ((RazorPageBase) pagesContactsDetails).BeginContext(915, 47, false);
      ((RazorPageBase) pagesContactsDetails).Write(pagesContactsDetails.Html.DisplayNameFor<string>((Expression<Func<DetailsModel, string>>) (model => model.Contact.Zip)));
      ((RazorPageBase) pagesContactsDetails).EndContext();
      ((RazorPageBase) pagesContactsDetails).BeginContext(962, 40, true);
      ((RazorPageBase) pagesContactsDetails).WriteLiteral("\n        </dt>\n        <dd>\n            ");
      ((RazorPageBase) pagesContactsDetails).EndContext();
      ((RazorPageBase) pagesContactsDetails).BeginContext(1003, 43, false);
      ((RazorPageBase) pagesContactsDetails).Write((object) HtmlHelperDisplayExtensions.DisplayFor<DetailsModel, string>(pagesContactsDetails.Html, (Expression<Func<DetailsModel, string>>) (model => model.Contact.Zip)));
      ((RazorPageBase) pagesContactsDetails).EndContext();
      ((RazorPageBase) pagesContactsDetails).BeginContext(1046, 42, true);
      ((RazorPageBase) pagesContactsDetails).WriteLiteral("\n        </dd>\n        <!-- <snippet> -->\n");
      ((RazorPageBase) pagesContactsDetails).EndContext();
      ((RazorPageBase) pagesContactsDetails).BeginContext(1137, 25, true);
      ((RazorPageBase) pagesContactsDetails).WriteLiteral("        <dt>\n            ");
      ((RazorPageBase) pagesContactsDetails).EndContext();
      ((RazorPageBase) pagesContactsDetails).BeginContext(1163, 49, false);
      ((RazorPageBase) pagesContactsDetails).Write(pagesContactsDetails.Html.DisplayNameFor<string>((Expression<Func<DetailsModel, string>>) (model => model.Contact.Email)));
      ((RazorPageBase) pagesContactsDetails).EndContext();
      ((RazorPageBase) pagesContactsDetails).BeginContext(1212, 40, true);
      ((RazorPageBase) pagesContactsDetails).WriteLiteral("\n        </dt>\n        <dd>\n            ");
      ((RazorPageBase) pagesContactsDetails).EndContext();
      ((RazorPageBase) pagesContactsDetails).BeginContext(1253, 45, false);
      ((RazorPageBase) pagesContactsDetails).Write((object) HtmlHelperDisplayExtensions.DisplayFor<DetailsModel, string>(pagesContactsDetails.Html, (Expression<Func<DetailsModel, string>>) (model => model.Contact.Email)));
      ((RazorPageBase) pagesContactsDetails).EndContext();
      ((RazorPageBase) pagesContactsDetails).BeginContext(1298, 40, true);
      ((RazorPageBase) pagesContactsDetails).WriteLiteral("\n        </dd>\n        <dt>\n            ");
      ((RazorPageBase) pagesContactsDetails).EndContext();
      ((RazorPageBase) pagesContactsDetails).BeginContext(1339, 50, false);
      ((RazorPageBase) pagesContactsDetails).Write(pagesContactsDetails.Html.DisplayNameFor<ContactStatus>((Expression<Func<DetailsModel, ContactStatus>>) (model => model.Contact.Status)));
      ((RazorPageBase) pagesContactsDetails).EndContext();
      ((RazorPageBase) pagesContactsDetails).BeginContext(1389, 40, true);
      ((RazorPageBase) pagesContactsDetails).WriteLiteral("\n        </dt>\n        <dd>\n            ");
      ((RazorPageBase) pagesContactsDetails).EndContext();
      ((RazorPageBase) pagesContactsDetails).BeginContext(1430, 46, false);
      ((RazorPageBase) pagesContactsDetails).Write((object) HtmlHelperDisplayExtensions.DisplayFor<DetailsModel, ContactStatus>(pagesContactsDetails.Html, (Expression<Func<DetailsModel, ContactStatus>>) (model => model.Contact.Status)));
      ((RazorPageBase) pagesContactsDetails).EndContext();
      ((RazorPageBase) pagesContactsDetails).BeginContext(1476, 33, true);
      ((RazorPageBase) pagesContactsDetails).WriteLiteral("\n        </dd>\n    </dl>\n</div>\n\n");
      ((RazorPageBase) pagesContactsDetails).EndContext();
      if (pagesContactsDetails.Model.Contact.Status != ContactStatus.Approved)
      {
        if ((await AuthorizationServiceExtensions.AuthorizeAsync(pagesContactsDetails.AuthorizationService, ((RazorPageBase) pagesContactsDetails).User, (object) pagesContactsDetails.Model.Contact, (IAuthorizationRequirement) ContactOperations.Approve)).Succeeded)
        {
          ((RazorPageBase) pagesContactsDetails).BeginContext(1688, 8, true);
          ((RazorPageBase) pagesContactsDetails).WriteLiteral("        ");
          ((RazorPageBase) pagesContactsDetails).EndContext();
          ((RazorPageBase) pagesContactsDetails).BeginContext(1696, 303, false);
          // ISSUE: reference to a compiler-generated method
          pagesContactsDetails.__tagHelperExecutionContext = pagesContactsDetails.__tagHelperScopeManager.Begin("form", (TagMode) 0, "9b3a5da276655e31ca2b7c43144e028ee5a1860c10796", new Func<Task>(pagesContactsDetails.\u003CExecuteAsync\u003Eb__13_14));
          pagesContactsDetails.__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper = ((RazorPageBase) pagesContactsDetails).CreateTagHelper<FormTagHelper>();
          pagesContactsDetails.__tagHelperExecutionContext.Add((ITagHelper) pagesContactsDetails.__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper);
          pagesContactsDetails.__Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper = ((RazorPageBase) pagesContactsDetails).CreateTagHelper<RenderAtEndOfFormTagHelper>();
          pagesContactsDetails.__tagHelperExecutionContext.Add((ITagHelper) pagesContactsDetails.__Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper);
          pagesContactsDetails.__tagHelperExecutionContext.AddHtmlAttribute(Pages_Contacts_Details.__tagHelperAttribute_0);
          pagesContactsDetails.__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper.Method = (string) Pages_Contacts_Details.__tagHelperAttribute_1.Value;
          pagesContactsDetails.__tagHelperExecutionContext.AddTagHelperAttribute(Pages_Contacts_Details.__tagHelperAttribute_1);
          await pagesContactsDetails.__tagHelperRunner.RunAsync(pagesContactsDetails.__tagHelperExecutionContext);
          if (!pagesContactsDetails.__tagHelperExecutionContext.Output.IsContentModified)
            await pagesContactsDetails.__tagHelperExecutionContext.SetOutputContentAsync();
          ((RazorPageBase) pagesContactsDetails).Write((object) pagesContactsDetails.__tagHelperExecutionContext.Output);
          pagesContactsDetails.__tagHelperExecutionContext = pagesContactsDetails.__tagHelperScopeManager.End();
          ((RazorPageBase) pagesContactsDetails).EndContext();
          ((RazorPageBase) pagesContactsDetails).BeginContext(1999, 1, true);
          ((RazorPageBase) pagesContactsDetails).WriteLiteral("\n");
          ((RazorPageBase) pagesContactsDetails).EndContext();
        }
      }
      ((RazorPageBase) pagesContactsDetails).BeginContext(2008, 1, true);
      ((RazorPageBase) pagesContactsDetails).WriteLiteral("\n");
      ((RazorPageBase) pagesContactsDetails).EndContext();
      if (pagesContactsDetails.Model.Contact.Status != ContactStatus.Rejected)
      {
        if ((await AuthorizationServiceExtensions.AuthorizeAsync(pagesContactsDetails.AuthorizationService, ((RazorPageBase) pagesContactsDetails).User, (object) pagesContactsDetails.Model.Contact, (IAuthorizationRequirement) ContactOperations.Reject)).Succeeded)
        {
          ((RazorPageBase) pagesContactsDetails).BeginContext(2187, 8, true);
          ((RazorPageBase) pagesContactsDetails).WriteLiteral("        ");
          ((RazorPageBase) pagesContactsDetails).EndContext();
          ((RazorPageBase) pagesContactsDetails).BeginContext(2195, 302, false);
          // ISSUE: reference to a compiler-generated method
          pagesContactsDetails.__tagHelperExecutionContext = pagesContactsDetails.__tagHelperScopeManager.Begin("form", (TagMode) 0, "9b3a5da276655e31ca2b7c43144e028ee5a1860c14146", new Func<Task>(pagesContactsDetails.\u003CExecuteAsync\u003Eb__13_15));
          pagesContactsDetails.__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper = ((RazorPageBase) pagesContactsDetails).CreateTagHelper<FormTagHelper>();
          pagesContactsDetails.__tagHelperExecutionContext.Add((ITagHelper) pagesContactsDetails.__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper);
          pagesContactsDetails.__Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper = ((RazorPageBase) pagesContactsDetails).CreateTagHelper<RenderAtEndOfFormTagHelper>();
          pagesContactsDetails.__tagHelperExecutionContext.Add((ITagHelper) pagesContactsDetails.__Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper);
          pagesContactsDetails.__tagHelperExecutionContext.AddHtmlAttribute(Pages_Contacts_Details.__tagHelperAttribute_0);
          pagesContactsDetails.__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper.Method = (string) Pages_Contacts_Details.__tagHelperAttribute_1.Value;
          pagesContactsDetails.__tagHelperExecutionContext.AddTagHelperAttribute(Pages_Contacts_Details.__tagHelperAttribute_1);
          await pagesContactsDetails.__tagHelperRunner.RunAsync(pagesContactsDetails.__tagHelperExecutionContext);
          if (!pagesContactsDetails.__tagHelperExecutionContext.Output.IsContentModified)
            await pagesContactsDetails.__tagHelperExecutionContext.SetOutputContentAsync();
          ((RazorPageBase) pagesContactsDetails).Write((object) pagesContactsDetails.__tagHelperExecutionContext.Output);
          pagesContactsDetails.__tagHelperExecutionContext = pagesContactsDetails.__tagHelperScopeManager.End();
          ((RazorPageBase) pagesContactsDetails).EndContext();
          ((RazorPageBase) pagesContactsDetails).BeginContext(2497, 1, true);
          ((RazorPageBase) pagesContactsDetails).WriteLiteral("\n");
          ((RazorPageBase) pagesContactsDetails).EndContext();
        }
      }
      ((RazorPageBase) pagesContactsDetails).BeginContext(2506, 7, true);
      ((RazorPageBase) pagesContactsDetails).WriteLiteral("\n<div>\n");
      ((RazorPageBase) pagesContactsDetails).EndContext();
      if ((await AuthorizationServiceExtensions.AuthorizeAsync(pagesContactsDetails.AuthorizationService, ((RazorPageBase) pagesContactsDetails).User, (object) pagesContactsDetails.Model.Contact, (IAuthorizationRequirement) ContactOperations.Update)).Succeeded)
      {
        ((RazorPageBase) pagesContactsDetails).BeginContext(2649, 8, true);
        ((RazorPageBase) pagesContactsDetails).WriteLiteral("        ");
        ((RazorPageBase) pagesContactsDetails).EndContext();
        ((RazorPageBase) pagesContactsDetails).BeginContext(2657, 69, false);
        // ISSUE: reference to a compiler-generated method
        pagesContactsDetails.__tagHelperExecutionContext = pagesContactsDetails.__tagHelperScopeManager.Begin("a", (TagMode) 0, "9b3a5da276655e31ca2b7c43144e028ee5a1860c17380", new Func<Task>(pagesContactsDetails.\u003CExecuteAsync\u003Eb__13_16));
        pagesContactsDetails.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper = ((RazorPageBase) pagesContactsDetails).CreateTagHelper<AnchorTagHelper>();
        pagesContactsDetails.__tagHelperExecutionContext.Add((ITagHelper) pagesContactsDetails.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper);
        pagesContactsDetails.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper.Page = (string) Pages_Contacts_Details.__tagHelperAttribute_2.Value;
        pagesContactsDetails.__tagHelperExecutionContext.AddTagHelperAttribute(Pages_Contacts_Details.__tagHelperAttribute_2);
        if (pagesContactsDetails.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper.RouteValues == null)
          throw new InvalidOperationException(((RazorPageBase) pagesContactsDetails).InvalidTagHelperIndexerAssignment("asp-route-id", "Microsoft.AspNetCore.Mvc.TagHelpers.AnchorTagHelper", "RouteValues"));
        ((RazorPageBase) pagesContactsDetails).BeginWriteTagHelperAttribute();
        ((RazorPageBase) pagesContactsDetails).WriteLiteral((object) pagesContactsDetails.Model.Contact.ContactId);
        pagesContactsDetails.__tagHelperStringValueBuffer = ((RazorPageBase) pagesContactsDetails).EndWriteTagHelperAttribute();
        pagesContactsDetails.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper.RouteValues["id"] = pagesContactsDetails.__tagHelperStringValueBuffer;
        pagesContactsDetails.__tagHelperExecutionContext.AddTagHelperAttribute("asp-route-id", (object) pagesContactsDetails.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper.RouteValues["id"], (HtmlAttributeValueStyle) 0);
        await pagesContactsDetails.__tagHelperRunner.RunAsync(pagesContactsDetails.__tagHelperExecutionContext);
        if (!pagesContactsDetails.__tagHelperExecutionContext.Output.IsContentModified)
          await pagesContactsDetails.__tagHelperExecutionContext.SetOutputContentAsync();
        ((RazorPageBase) pagesContactsDetails).Write((object) pagesContactsDetails.__tagHelperExecutionContext.Output);
        pagesContactsDetails.__tagHelperExecutionContext = pagesContactsDetails.__tagHelperScopeManager.End();
        ((RazorPageBase) pagesContactsDetails).EndContext();
        ((RazorPageBase) pagesContactsDetails).BeginContext(2726, 1, true);
        ((RazorPageBase) pagesContactsDetails).WriteLiteral("\n");
        ((RazorPageBase) pagesContactsDetails).EndContext();
        ((RazorPageBase) pagesContactsDetails).BeginContext(2741, 3, true);
        ((RazorPageBase) pagesContactsDetails).WriteLiteral(" | ");
        ((RazorPageBase) pagesContactsDetails).EndContext();
      }
      ((RazorPageBase) pagesContactsDetails).BeginContext(2758, 4, true);
      ((RazorPageBase) pagesContactsDetails).WriteLiteral("    ");
      ((RazorPageBase) pagesContactsDetails).EndContext();
      ((RazorPageBase) pagesContactsDetails).BeginContext(2762, 38, false);
      // ISSUE: reference to a compiler-generated method
      pagesContactsDetails.__tagHelperExecutionContext = pagesContactsDetails.__tagHelperScopeManager.Begin("a", (TagMode) 0, "9b3a5da276655e31ca2b7c43144e028ee5a1860c19937", new Func<Task>(pagesContactsDetails.\u003CExecuteAsync\u003Eb__13_17));
      pagesContactsDetails.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper = ((RazorPageBase) pagesContactsDetails).CreateTagHelper<AnchorTagHelper>();
      pagesContactsDetails.__tagHelperExecutionContext.Add((ITagHelper) pagesContactsDetails.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper);
      pagesContactsDetails.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper.Page = (string) Pages_Contacts_Details.__tagHelperAttribute_3.Value;
      pagesContactsDetails.__tagHelperExecutionContext.AddTagHelperAttribute(Pages_Contacts_Details.__tagHelperAttribute_3);
      await pagesContactsDetails.__tagHelperRunner.RunAsync(pagesContactsDetails.__tagHelperExecutionContext);
      if (!pagesContactsDetails.__tagHelperExecutionContext.Output.IsContentModified)
        await pagesContactsDetails.__tagHelperExecutionContext.SetOutputContentAsync();
      ((RazorPageBase) pagesContactsDetails).Write((object) pagesContactsDetails.__tagHelperExecutionContext.Output);
      pagesContactsDetails.__tagHelperExecutionContext = pagesContactsDetails.__tagHelperScopeManager.End();
      ((RazorPageBase) pagesContactsDetails).EndContext();
      ((RazorPageBase) pagesContactsDetails).BeginContext(2800, 27, true);
      ((RazorPageBase) pagesContactsDetails).WriteLiteral("\n</div>\n<!-- </snippet> -->");
      ((RazorPageBase) pagesContactsDetails).EndContext();
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
    public IHtmlHelper<DetailsModel> Html { get; private set; }

    public ViewDataDictionary<DetailsModel> ViewData
    {
      get => (ViewDataDictionary<DetailsModel>) ((PageBase) this).PageContext?.ViewData;
    }

    public DetailsModel Model => this.ViewData.Model;
  }
}
