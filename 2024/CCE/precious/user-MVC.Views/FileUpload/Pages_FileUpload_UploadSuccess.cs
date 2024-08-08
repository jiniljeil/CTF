// Decompiled with JetBrains decompiler
// Type: MVC.Pages.FileUpload.Pages_FileUpload_UploadSuccess
// Assembly: MVC.Views, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: 7837EB8D-71E8-4B9C-97FF-9247DC8E94C2
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.Views.dll

using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Razor;
using Microsoft.AspNetCore.Mvc.Razor.Internal;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.AspNetCore.Mvc.ViewFeatures;
using Microsoft.AspNetCore.Razor.Hosting;
using System.Threading.Tasks;

#nullable disable
namespace MVC.Pages.FileUpload
{
  [RazorSourceChecksum("SHA1", "308abd8b2ce5d7486099f32c36237ec91b6c4347", "/Pages/FileUpload/UploadSuccess.cshtml")]
  [RazorSourceChecksum("SHA1", "908c75dc3dcf7b54343c3ceaf8f93746d238aedc", "/Pages/_ViewImports.cshtml")]
  public class Pages_FileUpload_UploadSuccess : RazorPage<object>
  {
    public virtual async Task ExecuteAsync()
    {
      Pages_FileUpload_UploadSuccess uploadUploadSuccess = this;
      ((ViewDataDictionary) uploadUploadSuccess.ViewData)["Title"] = (object) "Upload Success";
      ((RazorPageBase) uploadUploadSuccess).BeginContext(47, 75, true);
      ((RazorPageBase) uploadUploadSuccess).WriteLiteral("\n<h2>Upload Success</h2>\n\n<p>Your file has been uploaded successfully.</p>\n");
      ((RazorPageBase) uploadUploadSuccess).EndContext();
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
