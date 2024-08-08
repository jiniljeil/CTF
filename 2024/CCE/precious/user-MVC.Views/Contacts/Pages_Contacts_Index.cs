// Decompiled with JetBrains decompiler
// Type: MVC.Pages.Contacts.Pages_Contacts_Index
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
using MVC.Authorization;
using MVC.Models;
using System;
using System.Collections.Generic;
using System.Linq.Expressions;
using System.Text.Encodings.Web;
using System.Threading.Tasks;

#nullable disable
namespace MVC.Pages.Contacts
{
  [RazorSourceChecksum("SHA1", "071a9d04aaee1d5503243ce62c2162ea5441fe6a", "/Pages/Contacts/Index.cshtml")]
  [RazorSourceChecksum("SHA1", "908c75dc3dcf7b54343c3ceaf8f93746d238aedc", "/Pages/_ViewImports.cshtml")]
  public class Pages_Contacts_Index : Page
  {
    private static readonly TagHelperAttribute __tagHelperAttribute_0 = new TagHelperAttribute("asp-page", (object) "Create", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_1 = new TagHelperAttribute("asp-page", (object) "./Edit", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_2 = new TagHelperAttribute("asp-page", (object) "./Details", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_3 = new TagHelperAttribute("asp-page", (object) "./Delete", (HtmlAttributeValueStyle) 0);
    private string __tagHelperStringValueBuffer;
    private TagHelperExecutionContext __tagHelperExecutionContext;
    private TagHelperRunner __tagHelperRunner = new TagHelperRunner();
    private TagHelperScopeManager __backed__tagHelperScopeManager;
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
      Pages_Contacts_Index pagesContactsIndex = this;
      ((RazorPageBase) pagesContactsIndex).BeginContext(43, 1, true);
      ((RazorPageBase) pagesContactsIndex).WriteLiteral("\n");
      ((RazorPageBase) pagesContactsIndex).EndContext();
      ((ViewDataDictionary) pagesContactsIndex.ViewData)["Title"] = (object) "Index";
      ((RazorPageBase) pagesContactsIndex).BeginContext(82, 25, true);
      ((RazorPageBase) pagesContactsIndex).WriteLiteral("\n<h2>Index</h2>\n\n<p>\n    ");
      ((RazorPageBase) pagesContactsIndex).EndContext();
      ((RazorPageBase) pagesContactsIndex).BeginContext(107, 35, false);
      // ISSUE: reference to a compiler-generated method
      pagesContactsIndex.__tagHelperExecutionContext = pagesContactsIndex.__tagHelperScopeManager.Begin("a", (TagMode) 0, "071a9d04aaee1d5503243ce62c2162ea5441fe6a4837", new Func<Task>(pagesContactsIndex.\u003CExecuteAsync\u003Eb__11_0));
      pagesContactsIndex.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper = ((RazorPageBase) pagesContactsIndex).CreateTagHelper<AnchorTagHelper>();
      pagesContactsIndex.__tagHelperExecutionContext.Add((ITagHelper) pagesContactsIndex.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper);
      pagesContactsIndex.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper.Page = (string) Pages_Contacts_Index.__tagHelperAttribute_0.Value;
      pagesContactsIndex.__tagHelperExecutionContext.AddTagHelperAttribute(Pages_Contacts_Index.__tagHelperAttribute_0);
      await pagesContactsIndex.__tagHelperRunner.RunAsync(pagesContactsIndex.__tagHelperExecutionContext);
      if (!pagesContactsIndex.__tagHelperExecutionContext.Output.IsContentModified)
        await pagesContactsIndex.__tagHelperExecutionContext.SetOutputContentAsync();
      ((RazorPageBase) pagesContactsIndex).Write((object) pagesContactsIndex.__tagHelperExecutionContext.Output);
      pagesContactsIndex.__tagHelperExecutionContext = pagesContactsIndex.__tagHelperScopeManager.End();
      ((RazorPageBase) pagesContactsIndex).EndContext();
      ((RazorPageBase) pagesContactsIndex).BeginContext(142, 86, true);
      ((RazorPageBase) pagesContactsIndex).WriteLiteral("\n</p>\n<table class=\"table\">\n    <thead>\n        <tr>\n            <th>\n                ");
      ((RazorPageBase) pagesContactsIndex).EndContext();
      ((RazorPageBase) pagesContactsIndex).BeginContext(229, 51, false);
      ((RazorPageBase) pagesContactsIndex).Write(pagesContactsIndex.Html.DisplayNameFor<string>((Expression<Func<IndexModel, string>>) (model => model.Contact[0].Name)));
      ((RazorPageBase) pagesContactsIndex).EndContext();
      ((RazorPageBase) pagesContactsIndex).BeginContext(280, 52, true);
      ((RazorPageBase) pagesContactsIndex).WriteLiteral("\n            </th>\n            <th>\n                ");
      ((RazorPageBase) pagesContactsIndex).EndContext();
      ((RazorPageBase) pagesContactsIndex).BeginContext(333, 54, false);
      ((RazorPageBase) pagesContactsIndex).Write(pagesContactsIndex.Html.DisplayNameFor<string>((Expression<Func<IndexModel, string>>) (model => model.Contact[0].Address)));
      ((RazorPageBase) pagesContactsIndex).EndContext();
      ((RazorPageBase) pagesContactsIndex).BeginContext(387, 52, true);
      ((RazorPageBase) pagesContactsIndex).WriteLiteral("\n            </th>\n            <th>\n                ");
      ((RazorPageBase) pagesContactsIndex).EndContext();
      ((RazorPageBase) pagesContactsIndex).BeginContext(440, 51, false);
      ((RazorPageBase) pagesContactsIndex).Write(pagesContactsIndex.Html.DisplayNameFor<string>((Expression<Func<IndexModel, string>>) (model => model.Contact[0].City)));
      ((RazorPageBase) pagesContactsIndex).EndContext();
      ((RazorPageBase) pagesContactsIndex).BeginContext(491, 52, true);
      ((RazorPageBase) pagesContactsIndex).WriteLiteral("\n            </th>\n            <th>\n                ");
      ((RazorPageBase) pagesContactsIndex).EndContext();
      ((RazorPageBase) pagesContactsIndex).BeginContext(544, 52, false);
      ((RazorPageBase) pagesContactsIndex).Write(pagesContactsIndex.Html.DisplayNameFor<string>((Expression<Func<IndexModel, string>>) (model => model.Contact[0].State)));
      ((RazorPageBase) pagesContactsIndex).EndContext();
      ((RazorPageBase) pagesContactsIndex).BeginContext(596, 52, true);
      ((RazorPageBase) pagesContactsIndex).WriteLiteral("\n            </th>\n            <th>\n                ");
      ((RazorPageBase) pagesContactsIndex).EndContext();
      ((RazorPageBase) pagesContactsIndex).BeginContext(649, 50, false);
      ((RazorPageBase) pagesContactsIndex).Write(pagesContactsIndex.Html.DisplayNameFor<string>((Expression<Func<IndexModel, string>>) (model => model.Contact[0].Zip)));
      ((RazorPageBase) pagesContactsIndex).EndContext();
      ((RazorPageBase) pagesContactsIndex).BeginContext(699, 52, true);
      ((RazorPageBase) pagesContactsIndex).WriteLiteral("\n            </th>\n            <th>\n                ");
      ((RazorPageBase) pagesContactsIndex).EndContext();
      ((RazorPageBase) pagesContactsIndex).BeginContext(752, 52, false);
      ((RazorPageBase) pagesContactsIndex).Write(pagesContactsIndex.Html.DisplayNameFor<string>((Expression<Func<IndexModel, string>>) (model => model.Contact[0].Email)));
      ((RazorPageBase) pagesContactsIndex).EndContext();
      ((RazorPageBase) pagesContactsIndex).BeginContext(804, 52, true);
      ((RazorPageBase) pagesContactsIndex).WriteLiteral("\n            </th>\n            <th>\n                ");
      ((RazorPageBase) pagesContactsIndex).EndContext();
      ((RazorPageBase) pagesContactsIndex).BeginContext(857, 53, false);
      ((RazorPageBase) pagesContactsIndex).Write(pagesContactsIndex.Html.DisplayNameFor<ContactStatus>((Expression<Func<IndexModel, ContactStatus>>) (model => model.Contact[0].Status)));
      ((RazorPageBase) pagesContactsIndex).EndContext();
      ((RazorPageBase) pagesContactsIndex).BeginContext(910, 80, true);
      ((RazorPageBase) pagesContactsIndex).WriteLiteral("\n            </th>\n            <th></th>\n        </tr>\n    </thead>\n    <tbody>\n");
      ((RazorPageBase) pagesContactsIndex).EndContext();
      foreach (Contact contact in (IEnumerable<Contact>) pagesContactsIndex.Model.Contact)
      {
        Contact item = contact;
        ((RazorPageBase) pagesContactsIndex).BeginContext(1045, 58, true);
        ((RazorPageBase) pagesContactsIndex).WriteLiteral("            <tr>\n                <td>\n                    ");
        ((RazorPageBase) pagesContactsIndex).EndContext();
        ((RazorPageBase) pagesContactsIndex).BeginContext(1104, 39, false);
        ((RazorPageBase) pagesContactsIndex).Write((object) HtmlHelperDisplayExtensions.DisplayFor<IndexModel, string>(pagesContactsIndex.Html, (Expression<Func<IndexModel, string>>) (modelItem => item.Name)));
        ((RazorPageBase) pagesContactsIndex).EndContext();
        ((RazorPageBase) pagesContactsIndex).BeginContext(1143, 64, true);
        ((RazorPageBase) pagesContactsIndex).WriteLiteral("\n                </td>\n                <td>\n                    ");
        ((RazorPageBase) pagesContactsIndex).EndContext();
        ((RazorPageBase) pagesContactsIndex).BeginContext(1208, 42, false);
        ((RazorPageBase) pagesContactsIndex).Write((object) HtmlHelperDisplayExtensions.DisplayFor<IndexModel, string>(pagesContactsIndex.Html, (Expression<Func<IndexModel, string>>) (modelItem => item.Address)));
        ((RazorPageBase) pagesContactsIndex).EndContext();
        ((RazorPageBase) pagesContactsIndex).BeginContext(1250, 64, true);
        ((RazorPageBase) pagesContactsIndex).WriteLiteral("\n                </td>\n                <td>\n                    ");
        ((RazorPageBase) pagesContactsIndex).EndContext();
        ((RazorPageBase) pagesContactsIndex).BeginContext(1315, 39, false);
        ((RazorPageBase) pagesContactsIndex).Write((object) HtmlHelperDisplayExtensions.DisplayFor<IndexModel, string>(pagesContactsIndex.Html, (Expression<Func<IndexModel, string>>) (modelItem => item.City)));
        ((RazorPageBase) pagesContactsIndex).EndContext();
        ((RazorPageBase) pagesContactsIndex).BeginContext(1354, 64, true);
        ((RazorPageBase) pagesContactsIndex).WriteLiteral("\n                </td>\n                <td>\n                    ");
        ((RazorPageBase) pagesContactsIndex).EndContext();
        ((RazorPageBase) pagesContactsIndex).BeginContext(1419, 40, false);
        ((RazorPageBase) pagesContactsIndex).Write((object) HtmlHelperDisplayExtensions.DisplayFor<IndexModel, string>(pagesContactsIndex.Html, (Expression<Func<IndexModel, string>>) (modelItem => item.State)));
        ((RazorPageBase) pagesContactsIndex).EndContext();
        ((RazorPageBase) pagesContactsIndex).BeginContext(1459, 64, true);
        ((RazorPageBase) pagesContactsIndex).WriteLiteral("\n                </td>\n                <td>\n                    ");
        ((RazorPageBase) pagesContactsIndex).EndContext();
        ((RazorPageBase) pagesContactsIndex).BeginContext(1524, 38, false);
        ((RazorPageBase) pagesContactsIndex).Write((object) HtmlHelperDisplayExtensions.DisplayFor<IndexModel, string>(pagesContactsIndex.Html, (Expression<Func<IndexModel, string>>) (modelItem => item.Zip)));
        ((RazorPageBase) pagesContactsIndex).EndContext();
        ((RazorPageBase) pagesContactsIndex).BeginContext(1562, 64, true);
        ((RazorPageBase) pagesContactsIndex).WriteLiteral("\n                </td>\n                <td>\n                    ");
        ((RazorPageBase) pagesContactsIndex).EndContext();
        ((RazorPageBase) pagesContactsIndex).BeginContext(1627, 40, false);
        ((RazorPageBase) pagesContactsIndex).Write((object) HtmlHelperDisplayExtensions.DisplayFor<IndexModel, string>(pagesContactsIndex.Html, (Expression<Func<IndexModel, string>>) (modelItem => item.Email)));
        ((RazorPageBase) pagesContactsIndex).EndContext();
        ((RazorPageBase) pagesContactsIndex).BeginContext(1667, 64, true);
        ((RazorPageBase) pagesContactsIndex).WriteLiteral("\n                </td>\n                <td>\n                    ");
        ((RazorPageBase) pagesContactsIndex).EndContext();
        ((RazorPageBase) pagesContactsIndex).BeginContext(1732, 41, false);
        ((RazorPageBase) pagesContactsIndex).Write((object) HtmlHelperDisplayExtensions.DisplayFor<IndexModel, ContactStatus>(pagesContactsIndex.Html, (Expression<Func<IndexModel, ContactStatus>>) (modelItem => item.Status)));
        ((RazorPageBase) pagesContactsIndex).EndContext();
        ((RazorPageBase) pagesContactsIndex).BeginContext(1773, 44, true);
        ((RazorPageBase) pagesContactsIndex).WriteLiteral("\n                </td>\n                <td>\n");
        ((RazorPageBase) pagesContactsIndex).EndContext();
        if ((await AuthorizationServiceExtensions.AuthorizeAsync(pagesContactsIndex.AuthorizationService, ((RazorPageBase) pagesContactsIndex).User, (object) item, (IAuthorizationRequirement) ContactOperations.Update)).Succeeded)
        {
          ((RazorPageBase) pagesContactsIndex).BeginContext(2000, 24, true);
          ((RazorPageBase) pagesContactsIndex).WriteLiteral("                        ");
          ((RazorPageBase) pagesContactsIndex).EndContext();
          ((RazorPageBase) pagesContactsIndex).BeginContext(2024, 60, false);
          // ISSUE: reference to a compiler-generated method
          pagesContactsIndex.__tagHelperExecutionContext = pagesContactsIndex.__tagHelperScopeManager.Begin("a", (TagMode) 0, "071a9d04aaee1d5503243ce62c2162ea5441fe6a12088", new Func<Task>(pagesContactsIndex.\u003CExecuteAsync\u003Eb__11_15));
          pagesContactsIndex.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper = ((RazorPageBase) pagesContactsIndex).CreateTagHelper<AnchorTagHelper>();
          pagesContactsIndex.__tagHelperExecutionContext.Add((ITagHelper) pagesContactsIndex.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper);
          pagesContactsIndex.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper.Page = (string) Pages_Contacts_Index.__tagHelperAttribute_1.Value;
          pagesContactsIndex.__tagHelperExecutionContext.AddTagHelperAttribute(Pages_Contacts_Index.__tagHelperAttribute_1);
          if (pagesContactsIndex.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper.RouteValues == null)
            throw new InvalidOperationException(((RazorPageBase) pagesContactsIndex).InvalidTagHelperIndexerAssignment("asp-route-id", "Microsoft.AspNetCore.Mvc.TagHelpers.AnchorTagHelper", "RouteValues"));
          ((RazorPageBase) pagesContactsIndex).BeginWriteTagHelperAttribute();
          ((RazorPageBase) pagesContactsIndex).WriteLiteral((object) item.ContactId);
          pagesContactsIndex.__tagHelperStringValueBuffer = ((RazorPageBase) pagesContactsIndex).EndWriteTagHelperAttribute();
          pagesContactsIndex.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper.RouteValues["id"] = pagesContactsIndex.__tagHelperStringValueBuffer;
          pagesContactsIndex.__tagHelperExecutionContext.AddTagHelperAttribute("asp-route-id", (object) pagesContactsIndex.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper.RouteValues["id"], (HtmlAttributeValueStyle) 0);
          await pagesContactsIndex.__tagHelperRunner.RunAsync(pagesContactsIndex.__tagHelperExecutionContext);
          if (!pagesContactsIndex.__tagHelperExecutionContext.Output.IsContentModified)
            await pagesContactsIndex.__tagHelperExecutionContext.SetOutputContentAsync();
          ((RazorPageBase) pagesContactsIndex).Write((object) pagesContactsIndex.__tagHelperExecutionContext.Output);
          pagesContactsIndex.__tagHelperExecutionContext = pagesContactsIndex.__tagHelperScopeManager.End();
          ((RazorPageBase) pagesContactsIndex).EndContext();
          ((RazorPageBase) pagesContactsIndex).BeginContext(2084, 1, true);
          ((RazorPageBase) pagesContactsIndex).WriteLiteral("\n");
          ((RazorPageBase) pagesContactsIndex).EndContext();
          ((RazorPageBase) pagesContactsIndex).BeginContext(2115, 3, true);
          ((RazorPageBase) pagesContactsIndex).WriteLiteral(" | ");
          ((RazorPageBase) pagesContactsIndex).EndContext();
        }
        ((RazorPageBase) pagesContactsIndex).BeginContext(2148, 21, true);
        ((RazorPageBase) pagesContactsIndex).WriteLiteral("\n                    ");
        ((RazorPageBase) pagesContactsIndex).EndContext();
        ((RazorPageBase) pagesContactsIndex).BeginContext(2169, 66, false);
        // ISSUE: reference to a compiler-generated method
        pagesContactsIndex.__tagHelperExecutionContext = pagesContactsIndex.__tagHelperScopeManager.Begin("a", (TagMode) 0, "071a9d04aaee1d5503243ce62c2162ea5441fe6a14699", new Func<Task>(pagesContactsIndex.\u003CExecuteAsync\u003Eb__11_16));
        pagesContactsIndex.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper = ((RazorPageBase) pagesContactsIndex).CreateTagHelper<AnchorTagHelper>();
        pagesContactsIndex.__tagHelperExecutionContext.Add((ITagHelper) pagesContactsIndex.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper);
        pagesContactsIndex.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper.Page = (string) Pages_Contacts_Index.__tagHelperAttribute_2.Value;
        pagesContactsIndex.__tagHelperExecutionContext.AddTagHelperAttribute(Pages_Contacts_Index.__tagHelperAttribute_2);
        if (pagesContactsIndex.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper.RouteValues == null)
          throw new InvalidOperationException(((RazorPageBase) pagesContactsIndex).InvalidTagHelperIndexerAssignment("asp-route-id", "Microsoft.AspNetCore.Mvc.TagHelpers.AnchorTagHelper", "RouteValues"));
        ((RazorPageBase) pagesContactsIndex).BeginWriteTagHelperAttribute();
        ((RazorPageBase) pagesContactsIndex).WriteLiteral((object) item.ContactId);
        pagesContactsIndex.__tagHelperStringValueBuffer = ((RazorPageBase) pagesContactsIndex).EndWriteTagHelperAttribute();
        pagesContactsIndex.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper.RouteValues["id"] = pagesContactsIndex.__tagHelperStringValueBuffer;
        pagesContactsIndex.__tagHelperExecutionContext.AddTagHelperAttribute("asp-route-id", (object) pagesContactsIndex.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper.RouteValues["id"], (HtmlAttributeValueStyle) 0);
        await pagesContactsIndex.__tagHelperRunner.RunAsync(pagesContactsIndex.__tagHelperExecutionContext);
        if (!pagesContactsIndex.__tagHelperExecutionContext.Output.IsContentModified)
          await pagesContactsIndex.__tagHelperExecutionContext.SetOutputContentAsync();
        ((RazorPageBase) pagesContactsIndex).Write((object) pagesContactsIndex.__tagHelperExecutionContext.Output);
        pagesContactsIndex.__tagHelperExecutionContext = pagesContactsIndex.__tagHelperScopeManager.End();
        ((RazorPageBase) pagesContactsIndex).EndContext();
        ((RazorPageBase) pagesContactsIndex).BeginContext(2235, 2, true);
        ((RazorPageBase) pagesContactsIndex).WriteLiteral("\n\n");
        ((RazorPageBase) pagesContactsIndex).EndContext();
        if ((await AuthorizationServiceExtensions.AuthorizeAsync(pagesContactsIndex.AuthorizationService, ((RazorPageBase) pagesContactsIndex).User, (object) item, (IAuthorizationRequirement) ContactOperations.Delete)).Succeeded)
        {
          ((RazorPageBase) pagesContactsIndex).BeginContext(2450, 3, true);
          ((RazorPageBase) pagesContactsIndex).WriteLiteral(" | ");
          ((RazorPageBase) pagesContactsIndex).EndContext();
          ((RazorPageBase) pagesContactsIndex).BeginContext(2460, 25, true);
          ((RazorPageBase) pagesContactsIndex).WriteLiteral("\n                        ");
          ((RazorPageBase) pagesContactsIndex).EndContext();
          ((RazorPageBase) pagesContactsIndex).BeginContext(2485, 64, false);
          // ISSUE: reference to a compiler-generated method
          pagesContactsIndex.__tagHelperExecutionContext = pagesContactsIndex.__tagHelperScopeManager.Begin("a", (TagMode) 0, "071a9d04aaee1d5503243ce62c2162ea5441fe6a17463", new Func<Task>(pagesContactsIndex.\u003CExecuteAsync\u003Eb__11_17));
          pagesContactsIndex.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper = ((RazorPageBase) pagesContactsIndex).CreateTagHelper<AnchorTagHelper>();
          pagesContactsIndex.__tagHelperExecutionContext.Add((ITagHelper) pagesContactsIndex.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper);
          pagesContactsIndex.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper.Page = (string) Pages_Contacts_Index.__tagHelperAttribute_3.Value;
          pagesContactsIndex.__tagHelperExecutionContext.AddTagHelperAttribute(Pages_Contacts_Index.__tagHelperAttribute_3);
          if (pagesContactsIndex.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper.RouteValues == null)
            throw new InvalidOperationException(((RazorPageBase) pagesContactsIndex).InvalidTagHelperIndexerAssignment("asp-route-id", "Microsoft.AspNetCore.Mvc.TagHelpers.AnchorTagHelper", "RouteValues"));
          ((RazorPageBase) pagesContactsIndex).BeginWriteTagHelperAttribute();
          ((RazorPageBase) pagesContactsIndex).WriteLiteral((object) item.ContactId);
          pagesContactsIndex.__tagHelperStringValueBuffer = ((RazorPageBase) pagesContactsIndex).EndWriteTagHelperAttribute();
          pagesContactsIndex.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper.RouteValues["id"] = pagesContactsIndex.__tagHelperStringValueBuffer;
          pagesContactsIndex.__tagHelperExecutionContext.AddTagHelperAttribute("asp-route-id", (object) pagesContactsIndex.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper.RouteValues["id"], (HtmlAttributeValueStyle) 0);
          await pagesContactsIndex.__tagHelperRunner.RunAsync(pagesContactsIndex.__tagHelperExecutionContext);
          if (!pagesContactsIndex.__tagHelperExecutionContext.Output.IsContentModified)
            await pagesContactsIndex.__tagHelperExecutionContext.SetOutputContentAsync();
          ((RazorPageBase) pagesContactsIndex).Write((object) pagesContactsIndex.__tagHelperExecutionContext.Output);
          pagesContactsIndex.__tagHelperExecutionContext = pagesContactsIndex.__tagHelperScopeManager.End();
          ((RazorPageBase) pagesContactsIndex).EndContext();
          ((RazorPageBase) pagesContactsIndex).BeginContext(2549, 1, true);
          ((RazorPageBase) pagesContactsIndex).WriteLiteral("\n");
          ((RazorPageBase) pagesContactsIndex).EndContext();
        }
        ((RazorPageBase) pagesContactsIndex).BeginContext(2572, 40, true);
        ((RazorPageBase) pagesContactsIndex).WriteLiteral("                </td>\n            </tr>\n");
        ((RazorPageBase) pagesContactsIndex).EndContext();
      }
      ((RazorPageBase) pagesContactsIndex).BeginContext(2622, 22, true);
      ((RazorPageBase) pagesContactsIndex).WriteLiteral("    </tbody>\n</table>\n");
      ((RazorPageBase) pagesContactsIndex).EndContext();
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
    public IHtmlHelper<IndexModel> Html { get; private set; }

    public ViewDataDictionary<IndexModel> ViewData
    {
      get => (ViewDataDictionary<IndexModel>) ((PageBase) this).PageContext?.ViewData;
    }

    public IndexModel Model => this.ViewData.Model;
  }
}
