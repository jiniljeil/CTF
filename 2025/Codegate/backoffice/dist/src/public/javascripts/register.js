import {API} from './API.js';

let api = await new API();

window.onload = () => {
   const register_submit = document.getElementById('register_submit');

   register_submit.addEventListener('click', async () => {
       const data = {
           name: document.getElementById('name').value,
           phone_number: document.getElementById('phone_number').value,
           birthday: document.getElementById('birthday').value,
           rrn: document.getElementById('rrn').value,
           email: document.getElementById('email').value,
           password: document.getElementById('password').value
       }

       try {
           const result = await api.communicate('POST', '/api/v1/register', {}, data, null);
           if (result.status === 201) {
               alert('register successfully');
               window.location.replace('/login');
           } else {
               throw new Error('register failed');
           }

       } catch (e) {
           alert(e.message.toString());
           window.location.reload();
       }
   });
}
