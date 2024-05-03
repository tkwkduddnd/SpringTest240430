console.log("comment js in");

//cmtAddBtn 버튼을 클릭하면 bno, writer, content를 비동기로 DB에 넣기

document.getElementById('cmtAddBtn').addEventListener('click', () => {
    const cmtText = document.getElementById('cmtText').value;
    const cmtWriter = document.getElementById('cmtWriter').innerText;

    if (cmtText == null || cmtText == '') {
        alert("댓글 입력하세요");
        document.getElementById('cmtText').focus();
        return false;
    } else {
        let cmtData = {
            bno: bnoVal,
            writer: cmtWriter,
            content: cmtText
        }
        console.log(cmtData);
        postCommentToServer(cmtData).then(result => {
            console.log(result);
            if (result == '1') {
                alert("댓글 등록 성공");
                document.getElementById('cmtText').value = '';
                spreadCommentList(bnoVal);
            }
        })
    }
})


async function postCommentToServer(cmtData) {
    try {
        const url = "/comment/post";
        const config = {
            method: "post",
            headers: {
                "content-type": "application/json; charset=utf-8"
            },
            body: JSON.stringify(cmtData)
        };

        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

// 댓글 뿌려주기
function spreadCommentList(bno, page = 1) {
    getCommentListFromServer(bno, page).then(result => {
        console.log(result);
        const div = document.getElementById('cmtListArea');
        if (result.cmtList.length > 0) {
            // div.innerHTML ="";
            if (page == 1) {
                div.innerHTML = "";
            }

            for (let cvo of result.cmtList) {
                let add = `<li class="list-group-item" data-cno="${cvo.cno}">`;
                add += `<div class="input-group mb-3">no. ${cvo.cno}`;
                add += `<div class="fw-bold">　${cvo.writer}　</div>${cvo.content}`;
                add += `</div>`;
                add += `<span class="badge rounded-pill text-bg-warning">${cvo.regDate}</span>`;
                add += `<button type="button" class="btn btn-primary btn-sm mod" data-bs-toggle="modal" data-bs-target="#myModal">수정</button>`;
                add += `<button type="button" class="btn btn-primary btn-sm del">삭제</button>`;
                add += `</li>`;
                div.innerHTML += add;
            }
            // 더보기 버튼 코드
            let moreBtn = document.getElementById('moreBtn');
            console.log(moreBtn);
            // moreBtn 표시되는 조건
            // pgvo.pageNo = 1 / realEndPage = 3
            // realEndPage보다 현재 내 페이지가 작으면 표시
            if (result.pgvo.pageNo < result.realEndPage) {
                // style = "visibility:hidden" = 숨김, visibility=:visible = 표시
                moreBtn.style.visibility = "visible"; // 버튼 표시
                moreBtn.dataset.page = page + 1; // 1페이지 늘림
            } else {
                moreBtn.style.visibility = "hidden";
            }

        } else {
            div.innerHTML = `<div class="list-group-item"> Comment List Empty </div>`;
        }
    })
}

async function getCommentListFromServer(bno, page) {
    try {
        // /comment/306
        const resp = await fetch("/comment/" + bno + "/" + page);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

document.addEventListener('click', (e) => {
    if (e.target.id == 'moreBtn') {
        let page = parseInt(e.target.dataset.page);
        spreadCommentList(bnoVal, page);
    } else if (e.target.classList.contains('mod')) {
        // 내가 수정 버튼을 누른 댓글의 li
        let li = e.target.closest('li');
        // nextSibling : 한 부모 안에서 다음 형제를 찾기
        let cmtText = li.querySelector('.fw-bold').nextSibling;
        console.log(cmtText);
        document.getElementById('cmtTextMod').value = cmtText.nodeValue;

        // 수정 => cno dataset으로 달기 cno, content
        document.getElementById('cmtModBtn').setAttribute("data-cno", li.dataset.cno);
    } else if (e.target.id == 'cmtModBtn') {
        let cmtModData = {
            cno: e.target.dataset.cno,
            content: document.getElementById('cmtTextMod').value
        };
        console.log(cmtModData);

        // 비동기로 보내기
        updateCommentToServer(cmtModData).then(result => {
            if (result == '1') {
                alert('댓글 수정 성공');
                document.querySelector(".btn-close").click();
                console.log(result);
            }
            else{
                alert('수정 실패');
                document.querySelector(".btn-close").click();
            }
            spreadCommentList(bnoVal);
        })

    } else if (e.target.classList.contains('del')) {
        let cnoVal = e.target.closest('li').dataset.cno;
        removeCommentFromServer(cnoVal).then(result => {
            if (result == '1') {
                alert('삭제 성공');
                spreadCommentList(bnoVal);
            }
        })
    }

});


async function updateCommentToServer(cmtModData) {
    try {
        const url = "/comment/modify";
        const config = {
            method: "put",
            headers: {
                'content-type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtModData)
        }

        const resp = await fetch(url, config);
        const result = resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function removeCommentFromServer(cnoVal) {
    try {
        const url = "/comment/"+cnoVal;
        const config = {
            method: "delete"
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}