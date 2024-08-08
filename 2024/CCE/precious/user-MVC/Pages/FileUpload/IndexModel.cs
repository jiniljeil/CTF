// Decompiled with JetBrains decompiler
// Type: MVC.Pages.FileUpload.IndexModel
// Assembly: MVC, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: C0D10978-DBF5-45D2-99BC-5BF002D0A709
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.dll

using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.IO;
using System.Net;
using System.Threading;
using System.Threading.Tasks;

#nullable disable
namespace MVC.Pages.FileUpload
{
  public class IndexModel : PageModel
  {
    private readonly IHttpContextAccessor _httpContextAccessor;
    private readonly ILogger<IndexModel> _logger;
    private readonly HashSet<string> _blacklistedExtensions = new HashSet<string>()
    {
      ".php",
      ".php4",
      ".php5",
      ".php7",
      ".php8",
      ".phtml",
      ".jsp",
      ".asp",
      ".aspx",
      ".cgi",
      ".pl",
      ".sh",
      ".rb",
      ".py",
      ".r",
      ".sql",
      ".java",
      ".cs",
      ".go",
      ".zip",
      ".tar.gz",
      ".gz"
    };
    private readonly HashSet<string> _allowedExtensions = new HashSet<string>()
    {
      ".png",
      ".jpg",
      ".jpeg",
      ".gif",
      ".pdf",
      ".docx",
      ".doc",
      ".xlsx",
      ".xls",
      ".txt"
    };

    [BindProperty]
    public IFormFile UploadFile { get; set; }

    public string UploadResult { get; set; }

    public IndexModel(IHttpContextAccessor httpContextAccessor, ILogger<IndexModel> logger)
    {
      this._httpContextAccessor = httpContextAccessor;
      this._logger = logger;
    }

    public void OnGet()
    {
    }

    public async Task<IActionResult> OnPostAsync()
    {
      IndexModel indexModel = this;
      int num = 10485760;
      IPAddress clientIpAddress = indexModel._httpContextAccessor.HttpContext.Connection.RemoteIpAddress;
      try
      {
        if (indexModel.UploadFile != null && indexModel.UploadFile.Length > 0L)
        {
          if (indexModel.UploadFile.Length > (long) num)
          {
            indexModel.UploadResult = "File Upload Failed";
            throw new Exception("File size exceeds the maximum allowed limit.");
          }
          string fileName = Path.GetFileName(indexModel.UploadFile.FileName);
          foreach (string blacklistedExtension in indexModel._blacklistedExtensions)
          {
            if (fileName.Contains(blacklistedExtension))
              fileName = fileName.Replace(blacklistedExtension, string.Empty);
          }
          if (string.IsNullOrWhiteSpace(fileName) || fileName == ".")
            fileName = Guid.NewGuid().ToString() + Path.GetExtension(indexModel.UploadFile.FileName);
          string str = Path.Combine("wwwroot", "uploads");
          if (!Directory.Exists(str))
            Directory.CreateDirectory(str);
          using (FileStream stream = new FileStream(Path.Combine(str, fileName), (FileMode) 2))
            await indexModel.UploadFile.CopyToAsync((Stream) stream, new CancellationToken());
          indexModel.UploadResult = string.Format("File '{0}' uploaded successfully! - {1} bytes", (object) fileName, (object) indexModel.UploadFile.Length);
          Console.WriteLine(indexModel.UploadResult);
          LoggerExtensions.LogInformation((ILogger) indexModel._logger, string.Format("[{0}] File uploaded from IP address: {1} -- '{2}'", (object) DateTimeOffset.Now.ToOffset(TimeSpan.FromHours(9.0)), (object) clientIpAddress, (object) fileName), Array.Empty<object>());
          fileName = (string) null;
        }
        else
        {
          indexModel.UploadResult = "No file uploaded.";
          Console.WriteLine(indexModel.UploadResult);
        }
      }
      catch (Exception ex)
      {
        indexModel.UploadResult = "File upload failed: " + ex.Message;
        Console.WriteLine("Exception: " + ex.Message);
      }
      return (IActionResult) indexModel.Page();
    }
  }
}
