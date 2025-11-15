import "./untrusted_content.js";

const shareButton = document.getElementById("share-with-admin");

async function share() {
  const body = document.querySelector("untrusted-content").html;
  const resp = await fetch(
    `/share-with-admin?body=${encodeURIComponent(body)}`
  );
  if (resp.status === 200) {
    alert("Admin shall see the message very soon");
  } else {
    alert("Something went wrong!");
  }
}

shareButton.addEventListener("click", () => share());
