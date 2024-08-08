// Decompiled with JetBrains decompiler
// Type: MVC.Pages.Pages_Error
// Assembly: MVC.Views, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: 7837EB8D-71E8-4B9C-97FF-9247DC8E94C2
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.Views.dll

using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Razor;
using Microsoft.AspNetCore.Mvc.Razor.Internal;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.AspNetCore.Mvc.ViewFeatures;
using Microsoft.AspNetCore.Razor.Hosting;
using System.Threading.Tasks;

#nullable disable
namespace MVC.Pages
{
  [RazorSourceChecksum("SHA1", "cc695e7b6fc60e9a6fcc9e9cd75eefa24d7b3f98", "/Pages/Error.cshtml")]
  [RazorSourceChecksum("SHA1", "908c75dc3dcf7b54343c3ceaf8f93746d238aedc", "/Pages/_ViewImports.cshtml")]
  public class Pages_Error : Page
  {
    public virtual async Task ExecuteAsync()
    {
      Pages_Error pagesError = this;
      ((ViewDataDictionary) pagesError.ViewData)["Title"] = (object) "Error";
      ((RazorPageBase) pagesError).BeginContext(62, 116, true);
      ((RazorPageBase) pagesError).WriteLiteral("\n<h1 class=\"text-danger\">Error.</h1>\n<h2 class=\"text-danger\">An error occurred while processing your request.</h2>\n\n");
      ((RazorPageBase) pagesError).EndContext();
      if (pagesError.Model.ShowRequestId)
      {
        ((RazorPageBase) pagesError).BeginContext(206, 51, true);
        ((RazorPageBase) pagesError).WriteLiteral("    <p>\n        <strong>Request ID:</strong> <code>");
        ((RazorPageBase) pagesError).EndContext();
        ((RazorPageBase) pagesError).BeginContext(258, 15, false);
        ((RazorPageBase) pagesError).Write(pagesError.Model.RequestId);
        ((RazorPageBase) pagesError).EndContext();
        ((RazorPageBase) pagesError).BeginContext(273, 17, true);
        ((RazorPageBase) pagesError).WriteLiteral("</code>\n    </p>\n");
        ((RazorPageBase) pagesError).EndContext();
      }
      ((RazorPageBase) pagesError).BeginContext(292, 561, true);
      ((RazorPageBase) pagesError).WriteLiteral("\n<h3>Development Mode</h3>\n<p>\n    Swapping to the <strong>Development</strong> environment displays detailed information about the error that occurred.\n</p>\n<p>\n    <strong>The Development environment shouldn't be enabled for deployed applications.</strong>\n    It can result in displaying sensitive information from exceptions to end users.\n    For local debugging, enable the <strong>Development</strong> environment by setting the <strong>ASPNETCORE_ENVIRONMENT</strong> environment variable to <strong>Development</strong>\n    and restarting the app.\n</p>\n");
      ((RazorPageBase) pagesError).EndContext();
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
    public IHtmlHelper<ErrorModel> Html { get; private set; }

    public ViewDataDictionary<ErrorModel> ViewData
    {
      get => (ViewDataDictionary<ErrorModel>) ((PageBase) this).PageContext?.ViewData;
    }

    public ErrorModel Model => this.ViewData.Model;
  }
}
