// Decompiled with JetBrains decompiler
// Type: MVC.Pages.Shared.Pages_Shared__Layout
// Assembly: MVC.Views, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: 7837EB8D-71E8-4B9C-97FF-9247DC8E94C2
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.Views.dll

using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Html;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Razor;
using Microsoft.AspNetCore.Mvc.Razor.Internal;
using Microsoft.AspNetCore.Mvc.Razor.TagHelpers;
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
  [RazorSourceChecksum("SHA1", "9ac1d896567d8b9091eef285ad157b15527049b6", "/Pages/Shared/_Layout.cshtml")]
  [RazorSourceChecksum("SHA1", "908c75dc3dcf7b54343c3ceaf8f93746d238aedc", "/Pages/_ViewImports.cshtml")]
  public class Pages_Shared__Layout : RazorPage<object>
  {
    private static readonly TagHelperAttribute __tagHelperAttribute_0 = new TagHelperAttribute("rel", (object) new HtmlString("stylesheet"), (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_1 = new TagHelperAttribute("href", (object) new HtmlString("~/lib/bootstrap/dist/css/bootstrap.css"), (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_2 = new TagHelperAttribute("href", (object) new HtmlString("~/css/site.css"), (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_3 = new TagHelperAttribute("include", (object) "Development", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_4 = new TagHelperAttribute("href", (object) "https://ajax.aspnetcdn.com/ajax/bootstrap/3.3.7/css/bootstrap.min.css", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_5 = new TagHelperAttribute("asp-fallback-href", (object) "~/lib/bootstrap/dist/css/bootstrap.min.css", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_6 = new TagHelperAttribute("asp-fallback-test-class", (object) "sr-only", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_7 = new TagHelperAttribute("asp-fallback-test-property", (object) "position", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_8 = new TagHelperAttribute("asp-fallback-test-value", (object) "absolute", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_9 = new TagHelperAttribute("exclude", (object) "Development", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_10 = new TagHelperAttribute("asp-page", (object) "/Contacts/Index", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_11 = new TagHelperAttribute("class", (object) new HtmlString("navbar-brand"), (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_12 = new TagHelperAttribute("asp-page", (object) "/Index", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_13 = new TagHelperAttribute("asp-page", (object) "/About", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_14 = new TagHelperAttribute("asp-page", (object) "/Contact", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_15 = new TagHelperAttribute("asp-controller", (object) "FileUpload", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_16 = new TagHelperAttribute("asp-action", (object) "Index", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_17 = new TagHelperAttribute("name", (object) "_LoginPartial", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_18 = new TagHelperAttribute("name", (object) "_CookieConsentPartial", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_19 = new TagHelperAttribute("src", (object) new HtmlString("~/lib/jquery/dist/jquery.js"), (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_20 = new TagHelperAttribute("src", (object) new HtmlString("~/lib/bootstrap/dist/js/bootstrap.js"), (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_21 = new TagHelperAttribute("src", (object) "https://ajax.aspnetcdn.com/ajax/jquery/jquery-3.3.1.min.js", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_22 = new TagHelperAttribute("asp-fallback-src", (object) "~/lib/jquery/dist/jquery.min.js", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_23 = new TagHelperAttribute("asp-fallback-test", (object) "window.jQuery", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_24 = new TagHelperAttribute("crossorigin", (object) new HtmlString("anonymous"), (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_25 = new TagHelperAttribute("integrity", (object) new HtmlString("sha384-tsQFqpEReu7ZLhBV2VZlAu7zcOV+rXbYlF2cqB8txI/8aZajjp4Bqd+V6D5IgvKT"), (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_26 = new TagHelperAttribute("src", (object) "https://ajax.aspnetcdn.com/ajax/bootstrap/3.3.7/bootstrap.min.js", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_27 = new TagHelperAttribute("asp-fallback-src", (object) "~/lib/bootstrap/dist/js/bootstrap.min.js", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_28 = new TagHelperAttribute("asp-fallback-test", (object) "window.jQuery && window.jQuery.fn && window.jQuery.fn.modal", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_29 = new TagHelperAttribute("integrity", (object) new HtmlString("sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"), (HtmlAttributeValueStyle) 0);
    private string __tagHelperStringValueBuffer;
    private TagHelperExecutionContext __tagHelperExecutionContext;
    private TagHelperRunner __tagHelperRunner = new TagHelperRunner();
    private TagHelperScopeManager __backed__tagHelperScopeManager;
    private HeadTagHelper __Microsoft_AspNetCore_Mvc_Razor_TagHelpers_HeadTagHelper;
    private EnvironmentTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_EnvironmentTagHelper;
    private UrlResolutionTagHelper __Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper;
    private LinkTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_LinkTagHelper;
    private BodyTagHelper __Microsoft_AspNetCore_Mvc_Razor_TagHelpers_BodyTagHelper;
    private AnchorTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper;
    private PartialTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_PartialTagHelper;
    private ScriptTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_ScriptTagHelper;

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
      Pages_Shared__Layout pagesSharedLayout = this;
      ((RazorPageBase) pagesSharedLayout).BeginContext(193, 23, true);
      ((RazorPageBase) pagesSharedLayout).WriteLiteral("<!DOCTYPE html>\n<html>\n");
      ((RazorPageBase) pagesSharedLayout).EndContext();
      ((RazorPageBase) pagesSharedLayout).BeginContext(216, 730, false);
      // ISSUE: reference to a compiler-generated method
      pagesSharedLayout.__tagHelperExecutionContext = pagesSharedLayout.__tagHelperScopeManager.Begin("head", (TagMode) 0, "9ac1d896567d8b9091eef285ad157b15527049b614649", new Func<Task>(pagesSharedLayout.\u003CExecuteAsync\u003Eb__44_0));
      pagesSharedLayout.__Microsoft_AspNetCore_Mvc_Razor_TagHelpers_HeadTagHelper = ((RazorPageBase) pagesSharedLayout).CreateTagHelper<HeadTagHelper>();
      pagesSharedLayout.__tagHelperExecutionContext.Add((ITagHelper) pagesSharedLayout.__Microsoft_AspNetCore_Mvc_Razor_TagHelpers_HeadTagHelper);
      await pagesSharedLayout.__tagHelperRunner.RunAsync(pagesSharedLayout.__tagHelperExecutionContext);
      if (!pagesSharedLayout.__tagHelperExecutionContext.Output.IsContentModified)
        await pagesSharedLayout.__tagHelperExecutionContext.SetOutputContentAsync();
      ((RazorPageBase) pagesSharedLayout).Write((object) pagesSharedLayout.__tagHelperExecutionContext.Output);
      pagesSharedLayout.__tagHelperExecutionContext = pagesSharedLayout.__tagHelperScopeManager.End();
      ((RazorPageBase) pagesSharedLayout).EndContext();
      ((RazorPageBase) pagesSharedLayout).BeginContext(946, 1, true);
      ((RazorPageBase) pagesSharedLayout).WriteLiteral("\n");
      ((RazorPageBase) pagesSharedLayout).EndContext();
      ((RazorPageBase) pagesSharedLayout).BeginContext(947, 2794, false);
      // ISSUE: reference to a compiler-generated method
      pagesSharedLayout.__tagHelperExecutionContext = pagesSharedLayout.__tagHelperScopeManager.Begin("body", (TagMode) 0, "9ac1d896567d8b9091eef285ad157b15527049b624336", new Func<Task>(pagesSharedLayout.\u003CExecuteAsync\u003Eb__44_1));
      pagesSharedLayout.__Microsoft_AspNetCore_Mvc_Razor_TagHelpers_BodyTagHelper = ((RazorPageBase) pagesSharedLayout).CreateTagHelper<BodyTagHelper>();
      pagesSharedLayout.__tagHelperExecutionContext.Add((ITagHelper) pagesSharedLayout.__Microsoft_AspNetCore_Mvc_Razor_TagHelpers_BodyTagHelper);
      await pagesSharedLayout.__tagHelperRunner.RunAsync(pagesSharedLayout.__tagHelperExecutionContext);
      if (!pagesSharedLayout.__tagHelperExecutionContext.Output.IsContentModified)
        await pagesSharedLayout.__tagHelperExecutionContext.SetOutputContentAsync();
      ((RazorPageBase) pagesSharedLayout).Write((object) pagesSharedLayout.__tagHelperExecutionContext.Output);
      pagesSharedLayout.__tagHelperExecutionContext = pagesSharedLayout.__tagHelperScopeManager.End();
      ((RazorPageBase) pagesSharedLayout).EndContext();
      ((RazorPageBase) pagesSharedLayout).BeginContext(3741, 9, true);
      ((RazorPageBase) pagesSharedLayout).WriteLiteral("\n</html>\n");
      ((RazorPageBase) pagesSharedLayout).EndContext();
    }

    [RazorInject]
    public Microsoft.AspNetCore.Identity.SignInManager<IdentityUser> SignInManager { get; private set; }

    [RazorInject]
    public Microsoft.AspNetCore.Identity.UserManager<IdentityUser> UserManager { get; private set; }

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
