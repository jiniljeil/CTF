// Decompiled with JetBrains decompiler
// Type: MVC.Migrations.ApplicationDbContextModelSnapshot
// Assembly: MVC, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: C0D10978-DBF5-45D2-99BC-5BF002D0A709
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.dll

using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using MVC.Data;
using System;

#nullable disable
namespace MVC.Migrations
{
  [DbContext(typeof (ApplicationDbContext))]
  internal class ApplicationDbContextModelSnapshot : ModelSnapshot
  {
    protected virtual void BuildModel(ModelBuilder modelBuilder)
    {
      modelBuilder.HasAnnotation("ProductVersion", (object) "2.2.6-servicing-10079").HasAnnotation("Relational:MaxIdentifierLength", (object) 128).HasAnnotation("SqlServer:ValueGenerationStrategy", (object) (SqlServerValueGenerationStrategy) 1);
      modelBuilder.Entity("MVC.Models.Contact", (Action<EntityTypeBuilder>) (b =>
      {
        b.Property<int>("ContactId").ValueGeneratedOnAdd().HasAnnotation("SqlServer:ValueGenerationStrategy", (object) (SqlServerValueGenerationStrategy) 1);
        b.Property<string>("Address");
        b.Property<string>("City");
        b.Property<string>("Email");
        b.Property<string>("Name");
        b.Property<string>("OwnerID");
        b.Property<string>("State");
        b.Property<int>("Status");
        b.Property<string>("Zip");
        b.HasKey(new string[1]{ "ContactId" });
        RelationalEntityTypeBuilderExtensions.ToTable(b, "Contact");
      }));
      modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityRole", (Action<EntityTypeBuilder>) (b =>
      {
        b.Property<string>("Id").ValueGeneratedOnAdd();
        b.Property<string>("ConcurrencyStamp").IsConcurrencyToken(true);
        b.Property<string>("Name").HasMaxLength(256);
        b.Property<string>("NormalizedName").HasMaxLength(256);
        b.HasKey(new string[1]{ "Id" });
        RelationalIndexBuilderExtensions.HasFilter(RelationalIndexBuilderExtensions.HasName(b.HasIndex(new string[1]
        {
          "NormalizedName"
        }).IsUnique(true), "RoleNameIndex"), "[NormalizedName] IS NOT NULL");
        RelationalEntityTypeBuilderExtensions.ToTable(b, "AspNetRoles");
      }));
      modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityRoleClaim<string>", (Action<EntityTypeBuilder>) (b =>
      {
        b.Property<int>("Id").ValueGeneratedOnAdd().HasAnnotation("SqlServer:ValueGenerationStrategy", (object) (SqlServerValueGenerationStrategy) 1);
        b.Property<string>("ClaimType");
        b.Property<string>("ClaimValue");
        b.Property<string>("RoleId").IsRequired(true);
        b.HasKey(new string[1]{ "Id" });
        b.HasIndex(new string[1]{ "RoleId" });
        RelationalEntityTypeBuilderExtensions.ToTable(b, "AspNetRoleClaims");
      }));
      modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityUser", (Action<EntityTypeBuilder>) (b =>
      {
        b.Property<string>("Id").ValueGeneratedOnAdd();
        b.Property<int>("AccessFailedCount");
        b.Property<string>("ConcurrencyStamp").IsConcurrencyToken(true);
        b.Property<string>("Email").HasMaxLength(256);
        b.Property<bool>("EmailConfirmed");
        b.Property<bool>("LockoutEnabled");
        b.Property<DateTimeOffset?>("LockoutEnd");
        b.Property<string>("NormalizedEmail").HasMaxLength(256);
        b.Property<string>("NormalizedUserName").HasMaxLength(256);
        b.Property<string>("PasswordHash");
        b.Property<string>("PhoneNumber");
        b.Property<bool>("PhoneNumberConfirmed");
        b.Property<string>("SecurityStamp");
        b.Property<bool>("TwoFactorEnabled");
        b.Property<string>("UserName").HasMaxLength(256);
        b.HasKey(new string[1]{ "Id" });
        RelationalIndexBuilderExtensions.HasName(b.HasIndex(new string[1]
        {
          "NormalizedEmail"
        }), "EmailIndex");
        RelationalIndexBuilderExtensions.HasFilter(RelationalIndexBuilderExtensions.HasName(b.HasIndex(new string[1]
        {
          "NormalizedUserName"
        }).IsUnique(true), "UserNameIndex"), "[NormalizedUserName] IS NOT NULL");
        RelationalEntityTypeBuilderExtensions.ToTable(b, "AspNetUsers");
      }));
      modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityUserClaim<string>", (Action<EntityTypeBuilder>) (b =>
      {
        b.Property<int>("Id").ValueGeneratedOnAdd().HasAnnotation("SqlServer:ValueGenerationStrategy", (object) (SqlServerValueGenerationStrategy) 1);
        b.Property<string>("ClaimType");
        b.Property<string>("ClaimValue");
        b.Property<string>("UserId").IsRequired(true);
        b.HasKey(new string[1]{ "Id" });
        b.HasIndex(new string[1]{ "UserId" });
        RelationalEntityTypeBuilderExtensions.ToTable(b, "AspNetUserClaims");
      }));
      modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityUserLogin<string>", (Action<EntityTypeBuilder>) (b =>
      {
        b.Property<string>("LoginProvider").HasMaxLength(128);
        b.Property<string>("ProviderKey").HasMaxLength(128);
        b.Property<string>("ProviderDisplayName");
        b.Property<string>("UserId").IsRequired(true);
        b.HasKey(new string[2]
        {
          "LoginProvider",
          "ProviderKey"
        });
        b.HasIndex(new string[1]{ "UserId" });
        RelationalEntityTypeBuilderExtensions.ToTable(b, "AspNetUserLogins");
      }));
      modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityUserRole<string>", (Action<EntityTypeBuilder>) (b =>
      {
        b.Property<string>("UserId");
        b.Property<string>("RoleId");
        b.HasKey(new string[2]{ "UserId", "RoleId" });
        b.HasIndex(new string[1]{ "RoleId" });
        RelationalEntityTypeBuilderExtensions.ToTable(b, "AspNetUserRoles");
      }));
      modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityUserToken<string>", (Action<EntityTypeBuilder>) (b =>
      {
        b.Property<string>("UserId");
        b.Property<string>("LoginProvider").HasMaxLength(128);
        b.Property<string>("Name").HasMaxLength(128);
        b.Property<string>("Value");
        b.HasKey(new string[3]
        {
          "UserId",
          "LoginProvider",
          "Name"
        });
        RelationalEntityTypeBuilderExtensions.ToTable(b, "AspNetUserTokens");
      }));
      modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityRoleClaim<string>", (Action<EntityTypeBuilder>) (b => b.HasOne("Microsoft.AspNetCore.Identity.IdentityRole", (string) null).WithMany((string) null).HasForeignKey(new string[1]
      {
        "RoleId"
      }).OnDelete((DeleteBehavior) 3)));
      modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityUserClaim<string>", (Action<EntityTypeBuilder>) (b => b.HasOne("Microsoft.AspNetCore.Identity.IdentityUser", (string) null).WithMany((string) null).HasForeignKey(new string[1]
      {
        "UserId"
      }).OnDelete((DeleteBehavior) 3)));
      modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityUserLogin<string>", (Action<EntityTypeBuilder>) (b => b.HasOne("Microsoft.AspNetCore.Identity.IdentityUser", (string) null).WithMany((string) null).HasForeignKey(new string[1]
      {
        "UserId"
      }).OnDelete((DeleteBehavior) 3)));
      modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityUserRole<string>", (Action<EntityTypeBuilder>) (b =>
      {
        b.HasOne("Microsoft.AspNetCore.Identity.IdentityRole", (string) null).WithMany((string) null).HasForeignKey(new string[1]
        {
          "RoleId"
        }).OnDelete((DeleteBehavior) 3);
        b.HasOne("Microsoft.AspNetCore.Identity.IdentityUser", (string) null).WithMany((string) null).HasForeignKey(new string[1]
        {
          "UserId"
        }).OnDelete((DeleteBehavior) 3);
      }));
      modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityUserToken<string>", (Action<EntityTypeBuilder>) (b => b.HasOne("Microsoft.AspNetCore.Identity.IdentityUser", (string) null).WithMany((string) null).HasForeignKey(new string[1]
      {
        "UserId"
      }).OnDelete((DeleteBehavior) 3)));
    }
  }
}
