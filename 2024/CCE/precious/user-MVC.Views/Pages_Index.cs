// Decompiled with JetBrains decompiler
// Type: MVC.Pages.Pages_Index
// Assembly: MVC.Views, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: 7837EB8D-71E8-4B9C-97FF-9247DC8E94C2
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.Views.dll

using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Html;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Razor;
using Microsoft.AspNetCore.Mvc.Razor.Internal;
using Microsoft.AspNetCore.Mvc.Razor.TagHelpers;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.AspNetCore.Mvc.ViewFeatures;
using Microsoft.AspNetCore.Razor.Hosting;
using Microsoft.AspNetCore.Razor.Runtime.TagHelpers;
using Microsoft.AspNetCore.Razor.TagHelpers;
using System;
using System.Text.Encodings.Web;
using System.Threading.Tasks;

#nullable disable
namespace MVC.Pages
{
  [RazorSourceChecksum("SHA1", "fb682a765ac2432799b8426bfa5916756be67fa6", "/Pages/Index.cshtml")]
  [RazorSourceChecksum("SHA1", "908c75dc3dcf7b54343c3ceaf8f93746d238aedc", "/Pages/_ViewImports.cshtml")]
  public class Pages_Index : Page
  {
    private static readonly TagHelperAttribute __tagHelperAttribute_0 = new TagHelperAttribute("src", (object) new HtmlString("~/images/banner1.svg"), (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_1 = new TagHelperAttribute("alt", (object) new HtmlString("ASP.NET"), (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_2 = new TagHelperAttribute("class", (object) new HtmlString("img-responsive"), (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_3 = new TagHelperAttribute("src", (object) new HtmlString("~/images/banner2.svg"), (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_4 = new TagHelperAttribute("alt", (object) new HtmlString("Visual Studio"), (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_5 = new TagHelperAttribute("src", (object) new HtmlString("~/images/banner3.svg"), (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_6 = new TagHelperAttribute("alt", (object) new HtmlString("Microsoft Azure"), (HtmlAttributeValueStyle) 0);
    private string __tagHelperStringValueBuffer;
    private TagHelperExecutionContext __tagHelperExecutionContext;
    private TagHelperRunner __tagHelperRunner = new TagHelperRunner();
    private TagHelperScopeManager __backed__tagHelperScopeManager;
    private UrlResolutionTagHelper __Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper;

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
      Pages_Index pagesIndex = this;
      ((ViewDataDictionary) pagesIndex.ViewData)["Title"] = (object) "Home page";
      ((RazorPageBase) pagesIndex).BeginContext(66, 430, true);
      ((RazorPageBase) pagesIndex).WriteLiteral("\n<div id=\"myCarousel\" class=\"carousel slide\" data-ride=\"carousel\" data-interval=\"6000\">\n    <ol class=\"carousel-indicators\">\n        <li data-target=\"#myCarousel\" data-slide-to=\"0\" class=\"active\"></li>\n        <li data-target=\"#myCarousel\" data-slide-to=\"1\"></li>\n        <li data-target=\"#myCarousel\" data-slide-to=\"2\"></li>\n    </ol>\n    <div class=\"carousel-inner\" role=\"listbox\">\n        <div class=\"item active\">\n            ");
      ((RazorPageBase) pagesIndex).EndContext();
      ((RazorPageBase) pagesIndex).BeginContext(496, 71, false);
      pagesIndex.__tagHelperExecutionContext = pagesIndex.__tagHelperScopeManager.Begin("img", (TagMode) 1, "fb682a765ac2432799b8426bfa5916756be67fa66377", (Func<Task>) (async () => { }));
      pagesIndex.__Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper = ((RazorPageBase) pagesIndex).CreateTagHelper<UrlResolutionTagHelper>();
      pagesIndex.__tagHelperExecutionContext.Add((ITagHelper) pagesIndex.__Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper);
      pagesIndex.__tagHelperExecutionContext.AddHtmlAttribute(Pages_Index.__tagHelperAttribute_0);
      pagesIndex.__tagHelperExecutionContext.AddHtmlAttribute(Pages_Index.__tagHelperAttribute_1);
      pagesIndex.__tagHelperExecutionContext.AddHtmlAttribute(Pages_Index.__tagHelperAttribute_2);
      await pagesIndex.__tagHelperRunner.RunAsync(pagesIndex.__tagHelperExecutionContext);
      if (!pagesIndex.__tagHelperExecutionContext.Output.IsContentModified)
        await pagesIndex.__tagHelperExecutionContext.SetOutputContentAsync();
      ((RazorPageBase) pagesIndex).Write((object) pagesIndex.__tagHelperExecutionContext.Output);
      pagesIndex.__tagHelperExecutionContext = pagesIndex.__tagHelperScopeManager.End();
      ((RazorPageBase) pagesIndex).EndContext();
      ((RazorPageBase) pagesIndex).BeginContext(567, 421, true);
      ((RazorPageBase) pagesIndex).WriteLiteral("\n            <div class=\"carousel-caption\" role=\"option\">\n                <p>\n                    Learn how to build ASP.NET apps that can run anywhere.\n                    <a class=\"btn btn-default\" href=\"https://go.microsoft.com/fwlink/?LinkID=525028&clcid=0x409\">\n                        Learn More\n                    </a>\n                </p>\n            </div>\n        </div>\n        <div class=\"item\">\n            ");
      ((RazorPageBase) pagesIndex).EndContext();
      ((RazorPageBase) pagesIndex).BeginContext(988, 77, false);
      pagesIndex.__tagHelperExecutionContext = pagesIndex.__tagHelperScopeManager.Begin("img", (TagMode) 1, "fb682a765ac2432799b8426bfa5916756be67fa68121", (Func<Task>) (async () => { }));
      pagesIndex.__Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper = ((RazorPageBase) pagesIndex).CreateTagHelper<UrlResolutionTagHelper>();
      pagesIndex.__tagHelperExecutionContext.Add((ITagHelper) pagesIndex.__Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper);
      pagesIndex.__tagHelperExecutionContext.AddHtmlAttribute(Pages_Index.__tagHelperAttribute_3);
      pagesIndex.__tagHelperExecutionContext.AddHtmlAttribute(Pages_Index.__tagHelperAttribute_4);
      pagesIndex.__tagHelperExecutionContext.AddHtmlAttribute(Pages_Index.__tagHelperAttribute_2);
      await pagesIndex.__tagHelperRunner.RunAsync(pagesIndex.__tagHelperExecutionContext);
      if (!pagesIndex.__tagHelperExecutionContext.Output.IsContentModified)
        await pagesIndex.__tagHelperExecutionContext.SetOutputContentAsync();
      ((RazorPageBase) pagesIndex).Write((object) pagesIndex.__tagHelperExecutionContext.Output);
      pagesIndex.__tagHelperExecutionContext = pagesIndex.__tagHelperScopeManager.End();
      ((RazorPageBase) pagesIndex).EndContext();
      ((RazorPageBase) pagesIndex).BeginContext(1065, 445, true);
      ((RazorPageBase) pagesIndex).WriteLiteral("\n            <div class=\"carousel-caption\" role=\"option\">\n                <p>\n                    There are powerful new features in Visual Studio for building modern web apps.\n                    <a class=\"btn btn-default\" href=\"https://go.microsoft.com/fwlink/?LinkID=525030&clcid=0x409\">\n                        Learn More\n                    </a>\n                </p>\n            </div>\n        </div>\n        <div class=\"item\">\n            ");
      ((RazorPageBase) pagesIndex).EndContext();
      ((RazorPageBase) pagesIndex).BeginContext(1510, 79, false);
      pagesIndex.__tagHelperExecutionContext = pagesIndex.__tagHelperScopeManager.Begin("img", (TagMode) 1, "fb682a765ac2432799b8426bfa5916756be67fa69891", (Func<Task>) (async () => { }));
      pagesIndex.__Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper = ((RazorPageBase) pagesIndex).CreateTagHelper<UrlResolutionTagHelper>();
      pagesIndex.__tagHelperExecutionContext.Add((ITagHelper) pagesIndex.__Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper);
      pagesIndex.__tagHelperExecutionContext.AddHtmlAttribute(Pages_Index.__tagHelperAttribute_5);
      pagesIndex.__tagHelperExecutionContext.AddHtmlAttribute(Pages_Index.__tagHelperAttribute_6);
      pagesIndex.__tagHelperExecutionContext.AddHtmlAttribute(Pages_Index.__tagHelperAttribute_2);
      await pagesIndex.__tagHelperRunner.RunAsync(pagesIndex.__tagHelperExecutionContext);
      if (!pagesIndex.__tagHelperExecutionContext.Output.IsContentModified)
        await pagesIndex.__tagHelperExecutionContext.SetOutputContentAsync();
      ((RazorPageBase) pagesIndex).Write((object) pagesIndex.__tagHelperExecutionContext.Output);
      pagesIndex.__tagHelperExecutionContext = pagesIndex.__tagHelperScopeManager.End();
      ((RazorPageBase) pagesIndex).EndContext();
      ((RazorPageBase) pagesIndex).BeginContext(1589, 3187, true);
      ((RazorPageBase) pagesIndex).WriteLiteral("\n            <div class=\"carousel-caption\" role=\"option\">\n                <p>\n                    Learn how Microsoft's Azure cloud platform allows you to build, deploy, and scale web apps.\n                    <a class=\"btn btn-default\" href=\"https://go.microsoft.com/fwlink/?LinkID=525027&clcid=0x409\">\n                        Learn More\n                    </a>\n                </p>\n            </div>\n        </div>\n    </div>\n    <a class=\"left carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"prev\">\n        <span class=\"glyphicon glyphicon-chevron-left\" aria-hidden=\"true\"></span>\n        <span class=\"sr-only\">Previous</span>\n    </a>\n    <a class=\"right carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"next\">\n        <span class=\"glyphicon glyphicon-chevron-right\" aria-hidden=\"true\"></span>\n        <span class=\"sr-only\">Next</span>\n    </a>\n</div>\n\n<div class=\"row\">\n    <div class=\"col-md-3\">\n        <h2>Application uses</h2>\n        <ul>\n            <li>Sample pages using ASP.NET C");
      ((RazorPageBase) pagesIndex).WriteLiteral("ore Razor Pages</li>\n            <li>Theming using <a href=\"https://go.microsoft.com/fwlink/?LinkID=398939\">Bootstrap</a></li>\n        </ul>\n    </div>\n    <div class=\"col-md-3\">\n        <h2>How to</h2>\n        <ul>\n            <li><a href=\"https://go.microsoft.com/fwlink/?linkid=852130\">Working with Razor Pages.</a></li>\n            <li><a href=\"https://go.microsoft.com/fwlink/?LinkId=699315\">Manage User Secrets using Secret Manager.</a></li>\n            <li><a href=\"https://go.microsoft.com/fwlink/?LinkId=699316\">Use logging to log a message.</a></li>\n            <li><a href=\"https://go.microsoft.com/fwlink/?LinkId=699317\">Add packages using NuGet.</a></li>\n            <li><a href=\"https://go.microsoft.com/fwlink/?LinkId=699319\">Target development, staging or production environment.</a></li>\n        </ul>\n    </div>\n    <div class=\"col-md-3\">\n        <h2>Overview</h2>\n        <ul>\n            <li><a href=\"https://go.microsoft.com/fwlink/?LinkId=518008\">Conceptual overview of what is ASP.NET Core</a></li>\n  ");
      ((RazorPageBase) pagesIndex).WriteLiteral("          <li><a href=\"https://go.microsoft.com/fwlink/?LinkId=699320\">Fundamentals of ASP.NET Core such as Startup and middleware.</a></li>\n            <li><a href=\"https://go.microsoft.com/fwlink/?LinkId=398602\">Working with Data</a></li>\n            <li><a href=\"https://go.microsoft.com/fwlink/?LinkId=398603\">Security</a></li>\n            <li><a href=\"https://go.microsoft.com/fwlink/?LinkID=699321\">Client side development</a></li>\n            <li><a href=\"https://go.microsoft.com/fwlink/?LinkID=699322\">Develop on different platforms</a></li>\n            <li><a href=\"https://go.microsoft.com/fwlink/?LinkID=699323\">Read more on the documentation site</a></li>\n        </ul>\n    </div>\n    <div class=\"col-md-3\">\n        <h2>Run &amp; Deploy</h2>\n        <ul>\n            <li><a href=\"https://go.microsoft.com/fwlink/?LinkID=517851\">Run your app</a></li>\n            <li><a href=\"https://go.microsoft.com/fwlink/?LinkID=517853\">Run tools such as EF migrations and more</a></li>\n            <li><a href=\"https://go.mi");
      ((RazorPageBase) pagesIndex).WriteLiteral("crosoft.com/fwlink/?LinkID=398609\">Publish to Microsoft Azure App Service</a></li>\n        </ul>\n    </div>\n</div>\n");
      ((RazorPageBase) pagesIndex).EndContext();
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
