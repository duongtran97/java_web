/**
 * Copyright(C) 2020 Luvina Software user.js, 16 Mar 2020 DuongTV
 */
var table = document.getElementsByClassName('levelJa');
function hiddenJapan() {
	for (var i = 0; i < table.length; i++) {
		table[i].style.display = 'none';
	}
}
function appearLevelJapan() {
	for (var i = 0; i < table.length; i++) {
		if (table[i].style.display == 'table-row') {
			table[i].style.display = 'none';
		} else {
			table[i].style.display = 'table-row';
		}
	}
}

function deleteUser() {
	return confirm("削除しますが、よろしいでしょうか。");
}
function backAction() {
	var accountName = document.getElementById("accountName").innerHTML;
	var url = '/15_tranvanduong_manage_user/AddUserInput.do?type=back'
			+ '&accountName=' + accountName;
	window.location.href = url;
}
function editUserInfor() {
	document.getElementById("accountName").readOnly = true;
	document.getElementById("password").style.display = 'none';
	document.getElementById("passwordConfirm").style.display = 'none';
	var userId = document.getElementById("user_id");
	window.location.href = '/15_tranvanduong_manage_user/EditUserInput.do?user_id='
			+ userId;
}