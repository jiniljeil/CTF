// Decompiled with JetBrains decompiler
// Type: MVC.Models.FileUploadViewModel
// Assembly: MVC, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: C0D10978-DBF5-45D2-99BC-5BF002D0A709
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.dll

using Microsoft.AspNetCore.Http;
using System.ComponentModel.DataAnnotations;

#nullable disable
namespace MVC.Models
{
  public class FileUploadViewModel
  {
    [Required]
    [Display(Name = "File")]
    public IFormFile File { get; set; }
  }
}
