// Decompiled with JetBrains decompiler
// Type: MVC.Pages.Shared.Pages_Shared__CookieConsentPartial
// Assembly: MVC.Views, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: 7837EB8D-71E8-4B9C-97FF-9247DC8E94C2
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.Views.dll

using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Html;
using Microsoft.AspNetCore.Http.Features;
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
  [RazorSourceChecksum("SHA1", "4cc9f76adee20ed6e885574dd7703de1b4ae3357", "/Pages/Shared/_CookieConsentPartial.cshtml")]
  [RazorSourceChecksum("SHA1", "908c75dc3dcf7b54343c3ceaf8f93746d238aedc", "/Pages/_ViewImports.cshtml")]
  public class Pages_Shared__CookieConsentPartial : RazorPage<object>
  {
    private static readonly TagHelperAttribute __tagHelperAttribute_0 = new TagHelperAttribute("asp-page", (object) "/Privacy", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_1 = new TagHelperAttribute("class", (object) new HtmlString("btn btn-info navbar-btn"), (HtmlAttributeValueStyle) 0);
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
      Pages_Shared__CookieConsentPartial cookieConsentPartial = this;
      ((RazorPageBase) cookieConsentPartial).BeginContext(42, 1, true);
      ((RazorPageBase) cookieConsentPartial).WriteLiteral("\n");
      ((RazorPageBase) cookieConsentPartial).EndContext();
      ITrackingConsentFeature itrackingConsentFeature = ((RazorPage) cookieConsentPartial).Context.Features.Get<ITrackingConsentFeature>();
      bool flag = itrackingConsentFeature != null && !itrackingConsentFeature.CanTrack;
      string cookieString = itrackingConsentFeature?.CreateConsentCookie();
      ((RazorPageBase) cookieConsentPartial).BeginContext(241, 1, true);
      ((RazorPageBase) cookieConsentPartial).WriteLiteral("\n");
      ((RazorPageBase) cookieConsentPartial).EndContext();
      if (!flag)
        return;
      ((RazorPageBase) cookieConsentPartial).BeginContext(261, 947, true);
      ((RazorPageBase) cookieConsentPartial).WriteLiteral("    <nav id=\"cookieConsent\" class=\"navbar navbar-default navbar-fixed-top\" role=\"alert\">\n        <div class=\"container\">\n            <div class=\"navbar-header\">\n                <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#cookieConsent .navbar-collapse\">\n                    <span class=\"sr-only\">Toggle cookie consent banner</span>\n                    <span class=\"icon-bar\"></span>\n                    <span class=\"icon-bar\"></span>\n                    <span class=\"icon-bar\"></span>\n                </button>\n                <span class=\"navbar-brand\"><span class=\"glyphicon glyphicon-info-sign\" aria-hidden=\"true\"></span></span>\n            </div>\n            <div class=\"collapse navbar-collapse\">\n                <p class=\"navbar-text\">\n                    Use this space to summarize your privacy and cookie use policy.\n                </p>\n                <div class=\"navbar-right\">\n                    ");
      ((RazorPageBase) cookieConsentPartial).EndContext();
      ((RazorPageBase) cookieConsentPartial).BeginContext(1208, 69, false);
      // ISSUE: reference to a compiler-generated method
      cookieConsentPartial.__tagHelperExecutionContext = cookieConsentPartial.__tagHelperScopeManager.Begin("a", (TagMode) 0, "4cc9f76adee20ed6e885574dd7703de1b4ae33575846", new Func<Task>(cookieConsentPartial.\u003CExecuteAsync\u003Eb__9_0));
      cookieConsentPartial.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper = ((RazorPageBase) cookieConsentPartial).CreateTagHelper<AnchorTagHelper>();
      cookieConsentPartial.__tagHelperExecutionContext.Add((ITagHelper) cookieConsentPartial.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper);
      cookieConsentPartial.__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper.Page = (string) Pages_Shared__CookieConsentPartial.__tagHelperAttribute_0.Value;
      cookieConsentPartial.__tagHelperExecutionContext.AddTagHelperAttribute(Pages_Shared__CookieConsentPartial.__tagHelperAttribute_0);
      cookieConsentPartial.__tagHelperExecutionContext.AddHtmlAttribute(Pages_Shared__CookieConsentPartial.__tagHelperAttribute_1);
      await cookieConsentPartial.__tagHelperRunner.RunAsync(cookieConsentPartial.__tagHelperExecutionContext);
      if (!cookieConsentPartial.__tagHelperExecutionContext.Output.IsContentModified)
        await cookieConsentPartial.__tagHelperExecutionContext.SetOutputContentAsync();
      ((RazorPageBase) cookieConsentPartial).Write((object) cookieConsentPartial.__tagHelperExecutionContext.Output);
      cookieConsentPartial.__tagHelperExecutionContext = cookieConsentPartial.__tagHelperScopeManager.End();
      ((RazorPageBase) cookieConsentPartial).EndContext();
      ((RazorPageBase) cookieConsentPartial).BeginContext(1277, 98, true);
      ((RazorPageBase) cookieConsentPartial).WriteLiteral("\n                    <button type=\"button\" class=\"btn btn-default navbar-btn\" data-cookie-string=\"");
      ((RazorPageBase) cookieConsentPartial).EndContext();
      ((RazorPageBase) cookieConsentPartial).BeginContext(1376, 12, false);
      ((RazorPageBase) cookieConsentPartial).Write(cookieString);
      ((RazorPageBase) cookieConsentPartial).EndContext();
      ((RazorPageBase) cookieConsentPartial).BeginContext(1388, 443, true);
      ((RazorPageBase) cookieConsentPartial).WriteLiteral("\">Accept</button>\n                </div>\n            </div>\n        </div>\n    </nav>\n    <script>\n        (function () {\n            document.querySelector(\"#cookieConsent button[data-cookie-string]\").addEventListener(\"click\", function (el) {\n                document.cookie = el.target.dataset.cookieString;\n                document.querySelector(\"#cookieConsent\").classList.add(\"hidden\");\n            }, false);\n        })();\n    </script>\n");
      ((RazorPageBase) cookieConsentPartial).EndContext();
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
    public IHtmlHelper<object> Html { get; private set; }
  }
}
