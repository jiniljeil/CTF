import {API} from './API.js';

let api = await new API();

export const qna_down = async (id, filename, role) => {
    const jwt_token = document.getElementById('jwt_token').value;
    let policy = "TEXT_DOWN";

    if (filename) {
        if (filename.endsWith("txt")) {
            policy = "TEXT_DOWN";
        }

        if (filename.endsWith("zip") || filename.endsWith('pdf')) {
            policy = "ETC_DOWN";
        }

        if (filename.endsWith("png") || filename.endsWith('jpg') || filename.endsWith('jpeg')) {
            policy = "IMAGE_DOWN";
        }
    }

    const data = {
        qna_id: id,
        dwn_policy: policy,
        dwn_strNm: filename,
        dwn_strView: 0
    }

    try {
        const result = await api.communicate("POST", `/api/v1/${role}/qna/file`, {Authorization: `bearer ${jwt_token}`}, data, null);
        if (result.status >= 400) {
            throw new Error('file not found');
        }

        const blobUrl = URL.createObjectURL(result.resp);
        window.open(blobUrl, "_blank");
        setTimeout(() => URL.revokeObjectURL(blobUrl), 30000);
    } catch (e) {
        alert(e.message.toString());
        window.location.reload();
    }
}

window.onload = () => {
    const role = document.getElementById('role').value;
    window.qna_down = qna_down;
    const logout = document.getElementById('logout');
    const user_update = document.getElementById('user_update');
    const mail_list = {
        inbox: document.getElementById('inbox-tab'),
        outbox: document.getElementById('outbox-tab'),
    }
    const send_mail = document.getElementById('send_mail');
    const qna_list = document.getElementById('qna-tab');
    const write_qna = document.getElementById('write_qna');
    const org_data = document.getElementById('org_data');
    const template_view = document.getElementById('view_template');
    const template_generate = document.getElementById('generate_template');

    if (logout) {
        logout.addEventListener('click', async () => {

            try {
                const jwt_token = document.getElementById('jwt_token').value;
                if (!jwt_token) {
                    throw new Error('JWT Token is invalid...');
                }
                await api.communicate("POST", "/api/v1/logout", {Authorization: `bearer ${jwt_token}`}, null, null);
                await api.communicate("GET", "/logout", {}, null, null);
            } catch (e) {
                alert(e.message.toString());
            }
            window.location.reload();
        });
    }

    if (user_update) {
        user_update.addEventListener('click', async () => {
            const role = document.getElementById('role').value;
            try {
                const jwt_token = document.getElementById('jwt_token').value;
                if (!jwt_token) {
                    throw new Error('JWT Token is invalid...');
                }

                const data = {
                    name: document.getElementById('name').value,
                    phone_number: document.getElementById('phone_number').value,
                    salary: document.getElementById('salary').value,
                    birthday: document.getElementById('birthday').value
                }
                const result = await api.communicate("PATCH", `/api/v1/${role}-update`, {Authorization: `bearer ${jwt_token}`}, data, null);
                alert(result.resp.data.msg)
            } catch (e) {
                alert(e.message.toString());
            }
            window.location.reload();
        });
    }

    if (mail_list.inbox || mail_list.outbox) {
        try {
            const jwt_token = document.getElementById('jwt_token').value;
            if (!jwt_token) {
                throw new Error('JWT Token is invalid...');
            }
            if (mail_list.inbox) {
                const role = document.getElementById('role').value;
                mail_list.inbox.addEventListener('click', async () => {
                    const inbox_data = document.getElementById('inbox_data');
                    inbox_data.innerHTML = "";
                    const result = await api.communicate('GET', `/api/v1/${role}/mail/inbox`, {Authorization: `bearer ${jwt_token}`}, null, null);
                    if (result.resp.data.data.inbox < 1) {
                        inbox_data.innerText = "Not Found";
                    }

                    result.resp.data.data.inbox.forEach((mail) => {
                        const keys = Object.keys(mail);
                        const row = document.createElement("tr");

                        keys.forEach(key => {
                            if (key !== 'is_read' && key !== 'updated_at') {
                                const cell = document.createElement("td");
                                if (key === 'body') {
                                    const textarea = document.createElement('textarea');
                                    textarea.setAttribute('rows', '10');
                                    textarea.setAttribute('cols', '60');
                                    textarea.setAttribute('style', 'resize:none');
                                    textarea.textContent = mail[key];
                                    cell.appendChild(textarea);
                                } else {
                                    cell.textContent = mail[key];
                                }


                                if (key === "id") {
                                    cell.setAttribute("id", mail[key]);
                                }

                                row.appendChild(cell);
                            }
                        });

                        inbox_data.appendChild(row);
                    })
                });
            }

            if (mail_list.outbox) {
                mail_list.outbox.addEventListener('click', async () => {
                    const role = document.getElementById('role').value;
                    const outbox_data = document.getElementById('outbox_data');
                    outbox_data.innerHTML = "";
                    const result = await api.communicate('GET', `/api/v1/${role}/mail/inbox`, {Authorization: `bearer ${jwt_token}`}, null, null);
                    if (result.resp.data.data.outbox < 1) {
                        outbox_data.innerText = "Not Found";
                    }

                    result.resp.data.data.outbox.forEach((mail) => {
                        const keys = Object.keys(mail);
                        const row = document.createElement("tr");

                        keys.forEach(key => {
                            if (key !== 'is_read' && key !== 'updated_at') {
                                const cell = document.createElement("td");

                                if (key === 'body') {
                                    const textarea = document.createElement('textarea');
                                    textarea.setAttribute('rows', '10');
                                    textarea.setAttribute('cols', '60');
                                    textarea.setAttribute('style', 'resize:none');
                                    textarea.textContent = mail[key];
                                    cell.appendChild(textarea);
                                } else {
                                    cell.textContent = mail[key];
                                }
                                if (key === "id") {
                                    cell.setAttribute("id", mail[key]);
                                }

                                row.appendChild(cell);
                            }
                        });

                        outbox_data.appendChild(row);
                    })
                });
            }
        } catch (e) {
            alert(e.message.toString());
            window.location.reload();
        }
    }

    if (send_mail) {
        send_mail.addEventListener('click', async () => {
            const role = document.getElementById('role').value;
            try {
                const jwt_token = document.getElementById('jwt_token').value;
                if (!jwt_token) {
                    throw new Error('JWT Token is invalid...');
                }

                const data = {
                    receiver_email: document.getElementById('receiver_email').value,
                    subject: document.getElementById('subject').value,
                    body: document.getElementById('mail_content').value
                };

                const result = await api.communicate('POST', `/api/v1/${role}/mail/sent`, {Authorization: `bearer ${jwt_token}`}, data, null);
                if (result.status !== 200) {
                    throw new Error('Mail send failed');
                }

                alert('successfully');
                window.location.reload();
            } catch (e) {
                alert(e.message.toString());
                window.location.reload();
            }
        })
    }

    if (qna_list) {
        qna_list.addEventListener('click', async () => {
            const role = document.getElementById('role').value;
            try {
                const jwt_token = document.getElementById('jwt_token').value;
                const qna_data = document.getElementById('qna_data');
                qna_data.innerHTML = "";
                const result = await api.communicate('GET', `/api/v1/${role}/qna`, {Authorization: `bearer ${jwt_token}`}, null, null);
                if (result.resp.data.data.length < 1) {
                    qna_data.innerText = "Not Found";
                }

                result.resp.data.data.forEach((qna) => {
                    const keys = Object.keys(qna);
                    const row = document.createElement("tr");

                    keys.forEach(key => {
                        const cell = document.createElement("td");
                        let id = "";
                        if (key !== "id" && key !== "file_name") {
                            cell.textContent = qna[key];
                        }

                        if (key === "id") {
                            cell.textContent = qna[key];
                            cell.setAttribute('id', qna[key]);
                        }
                        if (key === "file_name") {
                            if (qna[key] !== "N") {
                                const link = document.createElement("a");
                                link.textContent = qna[key];
                                link.setAttribute("id", `file-${qna['id']}`);
                                link.setAttribute("href", "#");
                                link.setAttribute("onclick", `qna_down("${qna['id']}", "${qna['file_name']}", "${role}")`);
                                cell.appendChild(link);
                            } else {
                                cell.textContent = qna[key];
                            }
                        }

                        row.appendChild(cell);
                    });
                    qna_data.appendChild(row);
                })
            } catch (e) {
                alert(e.message.toString());
                window.location.reload();
            }
        });
    }

    if (write_qna && role === 'admin') {
        write_qna.addEventListener('click', async () => {
            alert("Still in development");
            window.location.reload();
        });
    }

    if (write_qna && role === 'user') {
        write_qna.addEventListener('click', async () => {
            const role = document.getElementById('role').value;
            const jwt_token = document.getElementById('jwt_token').value;
            try {
                if (!jwt_token) {
                    throw new Error('JWT Token is invalid...');
                }

                const data = {
                    title: document.getElementById('qna_title').value,
                    password: document.getElementById('qna_password').value,
                    content: document.getElementById('qna_content').value,
                };

                let fileObj = document.getElementById('qna_file').files[0]
                let file = true;
                const formData = new FormData();
                if (!fileObj) {
                    formData.append("qna_file", new File([""], "", {type: "text/plain"}));
                    formData.append('title', data.title);
                    formData.append('password', data.password);
                    formData.append('content', data.content);
                } else {
                    formData.append("qna_file", fileObj);
                    formData.append('title', data.title);
                    formData.append('password', data.password);
                    formData.append('content', data.content);
                }
                const result = await api.communicate('POST', `/api/v1/${role}/qna`, {Authorization: `bearer ${jwt_token}`}, formData, file);
                if (result.status !== 200) {
                    throw new Error('QnA write failed');
                }

                alert('successfully');
                window.location.reload();
            } catch (e) {
                alert(e.message.toString());
                window.location.reload();
            }
        })
    }

    if (org_data) {
        (async () => {
            try {
                const role = document.getElementById('role').value;
                const jwt_token = document.getElementById('jwt_token').value;
                if (!jwt_token) {
                    throw new Error('JWT Token is invalid...');
                }
                const org_data = document.getElementById('org_data');
                org_data.innerHTML = "";

                const result = await api.communicate('GET', `/api/v1/${role}/list`, {Authorization: `bearer ${jwt_token}`}, null, null);
                if (result.resp.data.data.length < 1) {
                    org_data.innerText = "Not Found";
                }

                result.resp.data.data.forEach((user) => {
                    const keys = Object.keys(user);
                    const row = document.createElement("tr");

                    keys.forEach(key => {
                        const cell = document.createElement("td");
                        cell.textContent = user[key];

                        row.appendChild(cell);
                    });
                    org_data.appendChild(row);
                })
            } catch (e) {
                alert(e.message.toString());
                window.location.reload();
            }

        })();
    }

    if (template_view) {
        template_view.addEventListener('click', async () => {
            const role = document.getElementById('role').value;
            try {
                const jwt_token = document.getElementById('jwt_token').value;
                if (!jwt_token) {
                    throw new Error('JWT Token is invalid...');
                }

                const template_result = document.getElementById("template_result");
                let result = null;
                if (role === 'user')
                    result = await api.communicate('GET', `/api/v1/${role}/mail/template/1`, {Authorization: `bearer ${jwt_token}`}, null, null);
                else
                    result = await api.communicate('GET', `/api/v1/${role}/mail/template/0`, {Authorization: `bearer ${jwt_token}`}, null, null);

                if (result.status >= 400) {
                    throw new Error('file not found');
                }

                template_result.value = result.resp.data.data
            } catch (e) {
                alert(e.message.toString());
                window.location.reload();
            }

        });
    }

    if (template_generate) {
        template_generate.addEventListener('click', async () => {
            const role = document.getElementById('role').value;
            try {
                const jwt_token = document.getElementById('jwt_token').value;
                if (!jwt_token) {
                    throw new Error('JWT Token is invalid...');
                }

                const template_result = document.getElementById("template_result");
                const data = {
                    template_id: document.getElementById('template_id').value,
                    data: {
                        name: document.getElementById('name').value,
                        team: document.getElementById('team').value,
                        salary: document.getElementById('salary').value
                    }
                };

                const result = await api.communicate('POST', `/api/v1/${role}/mail/template`, {Authorization: `bearer ${jwt_token}`}, data, null);
                if (result.status >= 400) {
                    throw new Error('file not found');
                }

                template_result.value = result.resp;
            } catch (e) {
                alert(e.message.toString());
                window.location.reload();
            }

        });
    }
}
