// Decompiled with JetBrains decompiler
// Type: MVC.Pages.FileUpload.Pages_FileUpload_Index
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
using System;
using System.Text.Encodings.Web;
using System.Threading.Tasks;

#nullable disable
namespace MVC.Pages.FileUpload
{
  [RazorSourceChecksum("SHA1", "7c8119bc90b13aa5ee60eda4dd4d265f61457e81", "/Pages/FileUpload/Index.cshtml")]
  [RazorSourceChecksum("SHA1", "908c75dc3dcf7b54343c3ceaf8f93746d238aedc", "/Pages/_ViewImports.cshtml")]
  public class Pages_FileUpload_Index : Page
  {
    private static readonly TagHelperAttribute __tagHelperAttribute_0 = new TagHelperAttribute("method", (object) "post", (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_1 = new TagHelperAttribute("enctype", (object) new HtmlString("multipart/form-data"), (HtmlAttributeValueStyle) 0);
    private static readonly TagHelperAttribute __tagHelperAttribute_2 = new TagHelperAttribute("asp-page-handler", (object) "FileUpload", (HtmlAttributeValueStyle) 0);
    private string __tagHelperStringValueBuffer;
    private TagHelperExecutionContext __tagHelperExecutionContext;
    private TagHelperRunner __tagHelperRunner = new TagHelperRunner();
    private TagHelperScopeManager __backed__tagHelperScopeManager;
    private FormTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper;
    private RenderAtEndOfFormTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper;

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
      Pages_FileUpload_Index pagesFileUploadIndex = this;
      ((ViewDataDictionary) pagesFileUploadIndex.ViewData)["Title"] = (object) "File Upload";
      ((RazorPageBase) pagesFileUploadIndex).BeginContext(89, 23, true);
      ((RazorPageBase) pagesFileUploadIndex).WriteLiteral("\n<h2>File Upload</h2>\n\n");
      ((RazorPageBase) pagesFileUploadIndex).EndContext();
      ((RazorPageBase) pagesFileUploadIndex).BeginContext(112, 315, false);
      // ISSUE: reference to a compiler-generated method
      pagesFileUploadIndex.__tagHelperExecutionContext = pagesFileUploadIndex.__tagHelperScopeManager.Begin("form", (TagMode) 0, "7c8119bc90b13aa5ee60eda4dd4d265f61457e814682", new Func<Task>(pagesFileUploadIndex.\u003CExecuteAsync\u003Eb__11_0));
      pagesFileUploadIndex.__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper = ((RazorPageBase) pagesFileUploadIndex).CreateTagHelper<FormTagHelper>();
      pagesFileUploadIndex.__tagHelperExecutionContext.Add((ITagHelper) pagesFileUploadIndex.__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper);
      pagesFileUploadIndex.__Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper = ((RazorPageBase) pagesFileUploadIndex).CreateTagHelper<RenderAtEndOfFormTagHelper>();
      pagesFileUploadIndex.__tagHelperExecutionContext.Add((ITagHelper) pagesFileUploadIndex.__Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper);
      pagesFileUploadIndex.__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper.Method = (string) Pages_FileUpload_Index.__tagHelperAttribute_0.Value;
      pagesFileUploadIndex.__tagHelperExecutionContext.AddTagHelperAttribute(Pages_FileUpload_Index.__tagHelperAttribute_0);
      pagesFileUploadIndex.__tagHelperExecutionContext.AddHtmlAttribute(Pages_FileUpload_Index.__tagHelperAttribute_1);
      pagesFileUploadIndex.__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper.PageHandler = (string) Pages_FileUpload_Index.__tagHelperAttribute_2.Value;
      pagesFileUploadIndex.__tagHelperExecutionContext.AddTagHelperAttribute(Pages_FileUpload_Index.__tagHelperAttribute_2);
      await pagesFileUploadIndex.__tagHelperRunner.RunAsync(pagesFileUploadIndex.__tagHelperExecutionContext);
      if (!pagesFileUploadIndex.__tagHelperExecutionContext.Output.IsContentModified)
        await pagesFileUploadIndex.__tagHelperExecutionContext.SetOutputContentAsync();
      ((RazorPageBase) pagesFileUploadIndex).Write((object) pagesFileUploadIndex.__tagHelperExecutionContext.Output);
      pagesFileUploadIndex.__tagHelperExecutionContext = pagesFileUploadIndex.__tagHelperScopeManager.End();
      ((RazorPageBase) pagesFileUploadIndex).EndContext();
      ((RazorPageBase) pagesFileUploadIndex).BeginContext(427, 2, true);
      ((RazorPageBase) pagesFileUploadIndex).WriteLiteral("\n\n");
      ((RazorPageBase) pagesFileUploadIndex).EndContext();
      if (pagesFileUploadIndex.Model.UploadResult == null)
        return;
      ((RazorPageBase) pagesFileUploadIndex).BeginContext(464, 43, true);
      ((RazorPageBase) pagesFileUploadIndex).WriteLiteral("    <div class=\"alert alert-info\">\n        ");
      ((RazorPageBase) pagesFileUploadIndex).EndContext();
      ((RazorPageBase) pagesFileUploadIndex).BeginContext(508, 18, false);
      ((RazorPageBase) pagesFileUploadIndex).Write(pagesFileUploadIndex.Model.UploadResult);
      ((RazorPageBase) pagesFileUploadIndex).EndContext();
      ((RazorPageBase) pagesFileUploadIndex).BeginContext(526, 12, true);
      ((RazorPageBase) pagesFileUploadIndex).WriteLiteral("\n    </div>\n");
      ((RazorPageBase) pagesFileUploadIndex).EndContext();
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
