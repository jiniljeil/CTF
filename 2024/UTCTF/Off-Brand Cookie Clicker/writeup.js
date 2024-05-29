fetch('/click', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/x-www-form-urlencoded'
  },
  body: 'count=10000000'
}).then(response => response.json())
  .then(data => {
    alert(data.flag);
  });

// utflag{y0u_cl1ck_pr3tty_f4st}