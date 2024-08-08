// Decompiled with JetBrains decompiler
// Type: MVC.Pages.Shared.Pages_Shared__LoginPartial
// Assembly: MVC.Views, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: 7837EB8D-71E8-4B9C-97FF-9247DC8E94C2
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.Views.dll

using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Html;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Razor;
using Microsoft.AspNetCore.Mvc.Razor.Internal;
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
namespace MVC.Pages.Shared
{
  [RazorSourceChecksum("SHA1", "06c1c1158623b105357c92b17b1289267ce0a1e6", "/Pages/Shared/_LoginPartial.cshtml")]
  [RazorSourceChecksum("SHA1", "908c75dc3dcf7b54343c3ceaf8f93746d238aedc", "/Pages/_ViewImports.cshtml")]
  public class Pages_Shared__LoginPartial : RazorPage<object>
  {
    private static readonly TagHelperAttribute __tagHelperAttribute_0 = new TagHelperAttribute("asp-area", (object) "Identity", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_1 = new TagHelperAttribute("asp-page", (object) "/Account/Manage/Index", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_2 = new TagHelperAttribute("title", (object) new HtmlString("Manage"), (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_3 = new TagHelperAttribute("asp-page", (object) "/Account/Logout", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_4 = new TagHelperAttribute("method", (object) "post", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_5 = new TagHelperAttribute("id", (object) new HtmlString("logoutForm"), (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_6 = new TagHelperAttribute("class", (object) new HtmlString("navbar-right"), (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_7 = new TagHelperAttribute("asp-page", (object) "/Account/Register", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_8 = new TagHelperAttribute("asp-page", (object) "/Account/Login", (HtmlAttributeValueStyle) 0);
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
      Pages_Shared__LoginPartial sharedLoginPartial = this;
      ((RazorPageBase) sharedLoginPartial).BeginContext(37, 1, true);
      ((RazorPageBase) sharedLoginPartial).WriteLiteral("\n");
      ((RazorPageBase) sharedLoginPartial).EndContext();
      ((RazorPageBase) sharedLoginPartial).BeginContext(134, 1, true);
      ((RazorPageBase) sharedLoginPartial).WriteLiteral("\n");
      ((RazorPageBase) sharedLoginPartial).EndContext();
      if (sharedLoginPartial.SignInManager.IsSignedIn(((RazorPageBase) sharedLoginPartial).User))
      {
        ((RazorPageBase) sharedLoginPartial).BeginContext(174, 4, true);
        ((RazorPageBase) sharedLoginPartial).WriteLiteral("    ");
        ((RazorPageBase) sharedLoginPartial).EndContext();
        ((RazorPageBase) sharedLoginPartial).BeginContext(178, 537, false);
        // ISSUE: reference to a compiler-generated method
        sharedLoginPartial.__tagHelperExecutionContext = sharedLoginPartial.__tagHelperScopeManager.Begin("form", (TagMode) 0, "06c1c1158623b105357c92b17b1289267ce0a1e66907", new Func<Task>(sharedLoginPartial.\u003CExecuteAsync\u003Eb__18_0));
        sharedLoginPartial.__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper = ((RazorPageBase) sharedLoginPartial).CreateTagHelper<FormTagHelper>();
        sharedLoginPartial.__tagHelperExecutionContext.Add((ITagHelper) sharedLoginPartial.__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper);
        sharedLoginPartial.__Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper = ((RazorPageBase) sharedLoginPartial).CreateTagHelper<RenderAtEndOfFormTagHelper>();
        sharedLoginPartial.__tagHelperExecutionContext.Add((ITagHelper) sharedLoginPartial.__Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper);
        sharedLoginPartial.__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper.Area = (string) Pages_Shared__LoginPartial.__tagHelperAttribute_0.Value;
        sharedLoginPartial.__tagHelperExecutionContext.AddTagHelperAttribute(Pages_Shared__LoginPartial.__tagHelperAttribute_0);
        sharedLoginPartial.__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper.Page = (string) Pages_Shared__LoginPartial.__tagHelperAttribute_3.Value;
        sharedLoginPartial.__tagHelperExecutionContext.AddTagHelperAttribute(Pages_Shared__LoginPartial.__tagHelperAttribute_3);
        if (sharedLoginPartial.__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper.RouteValues == null)
          throw new InvalidOperationException(((RazorPageBase) sharedLoginPartial).InvalidTagHelperIndexerAssignment("asp-route-returnUrl", "Microsoft.AspNetCore.Mvc.TagHelpers.FormTagHelper", "RouteValues"));
        ((RazorPageBase) sharedLoginPartial).BeginWriteTagHelperAttribute();
        ((RazorPageBase) sharedLoginPartial).WriteLiteral(UrlHelperExtensions.Page(sharedLoginPartial.Url, "/Index", (object) new
        {
          area = ""
        }));
        sharedLoginPartial.__tagHelperStringValueBuffer = ((RazorPageBase) sharedLoginPartial).EndWriteTagHelperAttribute();
        sharedLoginPartial.__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper.RouteValues["returnUrl"] = sharedLoginPartial.__tagHelperStringValueBuffer;
        sharedLoginPartial.__tagHelperExecutionContext.AddTagHelperAttribute("asp-route-returnUrl", (object) sharedLoginPartial.__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper.RouteValues["returnUrl"], (HtmlAttributeValueStyle) 0);
        sharedLoginPartial.__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper.Method = (string) Pages_Shared__LoginPartial.__tagHelperAttribute_4.Value;
        sharedLoginPartial.__tagHelperExecutionContext.AddTagHelperAttribute(Pages_Shared__LoginPartial.__tagHelperAttribute_4);
        sharedLoginPartial.__tagHelperExecutionContext.AddHtmlAttribute(Pages_Shared__LoginPartial.__tagHelperAttribute_5);
        sharedLoginPartial.__tagHelperExecutionContext.AddHtmlAttribute(Pages_Shared__LoginPartial.__tagHelperAttribute_6);
        await sharedLoginPartial.__tagHelperRunner.RunAsync(sharedLoginPartial.__tagHelperExecutionContext);
        if (!sharedLoginPartial.__tagHelperExecutionContext.Output.IsContentModified)
          await sharedLoginPartial.__tagHelperExecutionContext.SetOutputContentAsync();
        ((RazorPageBase) sharedLoginPartial).Write((object) sharedLoginPartial.__tagHelperExecutionContext.Output);
        sharedLoginPartial.__tagHelperExecutionContext = sharedLoginPartial.__tagHelperScopeManager.End();
        ((RazorPageBase) sharedLoginPartial).EndContext();
        ((RazorPageBase) sharedLoginPartial).BeginContext(715, 1, true);
        ((RazorPageBase) sharedLoginPartial).WriteLiteral("\n");
        ((RazorPageBase) sharedLoginPartial).EndContext();
      }
      else
      {
        ((RazorPageBase) sharedLoginPartial).BeginContext(725, 57, true);
        ((RazorPageBase) sharedLoginPartial).WriteLiteral("    <ul class=\"nav navbar-nav navbar-right\">\n        <li>");
        ((RazorPageBase) sharedLoginPartial).EndContext();
        ((RazorPageBase) sharedLoginPartial).BeginContext(782, 64, false);
        // ISSUE: reference to a compiler-generated method
        sharedLoginPartial.__tagHelperExecutionContext = sharedLoginPartial.__tagHelperScopeManager.Begin("a", (TagMode) 0, "06c1c1158623b105357c92b17b1289267ce0a1e612702", new Func<Task>(sharedLoginPartial.\u003CExecuteAsync\u003Eb__18_1));
        sharedLoginPartial.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper = ((RazorPageBase) sharedLoginPartial).CreateTagHelper<AnchorTagHelper>();
        sharedLoginPartial.__tagHelperExecutionContext.Add((ITagHelper) sharedLoginPartial.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper);
        sharedLoginPartial.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper.Area = (string) Pages_Shared__LoginPartial.__tagHelperAttribute_0.Value;
        sharedLoginPartial.__tagHelperExecutionContext.AddTagHelperAttribute(Pages_Shared__LoginPartial.__tagHelperAttribute_0);
        sharedLoginPartial.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper.Page = (string) Pages_Shared__LoginPartial.__tagHelperAttribute_7.Value;
        sharedLoginPartial.__tagHelperExecutionContext.AddTagHelperAttribute(Pages_Shared__LoginPartial.__tagHelperAttribute_7);
        await sharedLoginPartial.__tagHelperRunner.RunAsync(sharedLoginPartial.__tagHelperExecutionContext);
        if (!sharedLoginPartial.__tagHelperExecutionContext.Output.IsContentModified)
          await sharedLoginPartial.__tagHelperExecutionContext.SetOutputContentAsync();
        ((RazorPageBase) sharedLoginPartial).Write((object) sharedLoginPartial.__tagHelperExecutionContext.Output);
        sharedLoginPartial.__tagHelperExecutionContext = sharedLoginPartial.__tagHelperScopeManager.End();
        ((RazorPageBase) sharedLoginPartial).EndContext();
        ((RazorPageBase) sharedLoginPartial).BeginContext(846, 18, true);
        ((RazorPageBase) sharedLoginPartial).WriteLiteral("</li>\n        <li>");
        ((RazorPageBase) sharedLoginPartial).EndContext();
        ((RazorPageBase) sharedLoginPartial).BeginContext(864, 58, false);
        // ISSUE: reference to a compiler-generated method
        sharedLoginPartial.__tagHelperExecutionContext = sharedLoginPartial.__tagHelperScopeManager.Begin("a", (TagMode) 0, "06c1c1158623b105357c92b17b1289267ce0a1e614264", new Func<Task>(sharedLoginPartial.\u003CExecuteAsync\u003Eb__18_2));
        sharedLoginPartial.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper = ((RazorPageBase) sharedLoginPartial).CreateTagHelper<AnchorTagHelper>();
        sharedLoginPartial.__tagHelperExecutionContext.Add((ITagHelper) sharedLoginPartial.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper);
        sharedLoginPartial.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper.Area = (string) Pages_Shared__LoginPartial.__tagHelperAttribute_0.Value;
        sharedLoginPartial.__tagHelperExecutionContext.AddTagHelperAttribute(Pages_Shared__LoginPartial.__tagHelperAttribute_0);
        sharedLoginPartial.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper.Page = (string) Pages_Shared__LoginPartial.__tagHelperAttribute_8.Value;
        sharedLoginPartial.__tagHelperExecutionContext.AddTagHelperAttribute(Pages_Shared__LoginPartial.__tagHelperAttribute_8);
        await sharedLoginPartial.__tagHelperRunner.RunAsync(sharedLoginPartial.__tagHelperExecutionContext);
        if (!sharedLoginPartial.__tagHelperExecutionContext.Output.IsContentModified)
          await sharedLoginPartial.__tagHelperExecutionContext.SetOutputContentAsync();
        ((RazorPageBase) sharedLoginPartial).Write((object) sharedLoginPartial.__tagHelperExecutionContext.Output);
        sharedLoginPartial.__tagHelperExecutionContext = sharedLoginPartial.__tagHelperScopeManager.End();
        ((RazorPageBase) sharedLoginPartial).EndContext();
        ((RazorPageBase) sharedLoginPartial).BeginContext(922, 16, true);
        ((RazorPageBase) sharedLoginPartial).WriteLiteral("</li>\n    </ul>\n");
        ((RazorPageBase) sharedLoginPartial).EndContext();
      }
    }

    [RazorInject]
    public Microsoft.AspNetCore.Identity.UserManager<IdentityUser> UserManager { get; private set; }

    [RazorInject]
    public Microsoft.AspNetCore.Identity.SignInManager<IdentityUser> SignInManager { get; private set; }

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
    public IHtmlHelper<object> Html { get; private set; }
  }
}
