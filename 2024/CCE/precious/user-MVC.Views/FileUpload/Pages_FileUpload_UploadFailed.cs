// Decompiled with JetBrains decompiler
// Type: MVC.Pages.FileUpload.Pages_FileUpload_UploadFailed
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
namespace MVC.Pages.FileUpload
{
  [RazorSourceChecksum("SHA1", "39136773c450ad98677bddde6d2191bc587bf68c", "/Pages/FileUpload/UploadFailed.cshtml")]
  [RazorSourceChecksum("SHA1", "908c75dc3dcf7b54343c3ceaf8f93746d238aedc", "/Pages/_ViewImports.cshtml")]
  public class Pages_FileUpload_UploadFailed : Page
  {
    public virtual async Task ExecuteAsync()
    {
      Pages_FileUpload_UploadFailed uploadUploadFailed = this;
      ((RazorPageBase) uploadUploadFailed).BeginContext(24, 28, true);
      ((RazorPageBase) uploadUploadFailed).WriteLiteral("\n<h2>Upload Failed</h2>\n\n<p>");
      ((RazorPageBase) uploadUploadFailed).EndContext();
      ((RazorPageBase) uploadUploadFailed).BeginContext(53, 18, false);
      ((RazorPageBase) uploadUploadFailed).Write(uploadUploadFailed.Model.UploadResult);
      ((RazorPageBase) uploadUploadFailed).EndContext();
      ((RazorPageBase) uploadUploadFailed).BeginContext(71, 4, true);
      ((RazorPageBase) uploadUploadFailed).WriteLiteral("</p>");
      ((RazorPageBase) uploadUploadFailed).EndContext();
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
