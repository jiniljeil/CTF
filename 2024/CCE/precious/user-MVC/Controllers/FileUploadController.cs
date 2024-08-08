// Decompiled with JetBrains decompiler
// Type: MVC.Controllers.FileUploadController
// Assembly: MVC, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: C0D10978-DBF5-45D2-99BC-5BF002D0A709
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.dll

using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using MVC.Models;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;

#nullable disable
namespace MVC.Controllers
{
  public class FileUploadController : Controller
  {
    private readonly IHostingEnvironment _hostingEnvironment;
    private readonly string[] _permittedExtensions = new string[4]
    {
      ".txt",
      ".jpg",
      ".png",
      ".pdf"
    };

    public FileUploadController(IHostingEnvironment hostingEnvironment)
    {
      this._hostingEnvironment = hostingEnvironment;
    }

    public IActionResult Index() => (IActionResult) this.View();

    [HttpPost]
    public async Task<IActionResult> UploadFile(FileUploadViewModel model)
    {
      FileUploadController uploadController = this;
      if (!((ControllerBase) uploadController).ModelState.IsValid)
        return (IActionResult) uploadController.View("Index");
      IFormFile file = model.File;
      string lowerInvariant = Path.GetExtension(file.FileName).ToLowerInvariant();
      if (string.IsNullOrEmpty(lowerInvariant) || !((IEnumerable<string>) uploadController._permittedExtensions).Contains<string>(lowerInvariant))
      {
        ((ControllerBase) uploadController).ModelState.AddModelError("File", "Invalid file extension");
        return (IActionResult) uploadController.View("Index");
      }
      string uploadPath = Path.Combine(uploadController._hostingEnvironment.WebRootPath, "uploads");
      Directory.CreateDirectory(uploadPath);
      using (FileStream fileStream = new FileStream(Path.Combine(uploadPath, file.FileName), (FileMode) 2))
        await file.CopyToAsync((Stream) fileStream, new CancellationToken());
      await File.AppendAllTextAsync(Path.Combine(uploadPath, "log.txt"), string.Format("{0} uploaded at {1}\n", (object) file.FileName, (object) DateTime.Now), new CancellationToken());
      return (IActionResult) ((ControllerBase) uploadController).RedirectToAction("UploadSuccess");
    }

    public IActionResult UploadSuccess() => (IActionResult) this.View();
  }
}
