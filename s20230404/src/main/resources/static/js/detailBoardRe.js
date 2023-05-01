function showUpdate(button) {
   var membersRow = button.parentNode.parentNode.nextElementSibling;
   if (membersRow.classList.contains("members")) {
      if (membersRow.style.display == "none") {
         membersRow.style.display = "table-row";
      } else {
         membersRow.style.display = "none";
      }
   }
}


function showMembers(button) {
   var membersRow = button.parentNode.parentNode.nextElementSibling.nextElementSibling;
   if (membersRow.classList.contains("members")) {
      if (membersRow.style.display == "none") {
         membersRow.style.display = "table-row";
      } else {
         membersRow.style.display = "none";
      }
   }
}

function showReWarning(button) {
   var membersRow = button.parentNode.parentNode.nextElementSibling.nextElementSibling.nextElementSibling;
   if (membersRow.classList.contains("members")) {
      if (membersRow.style.display == "none") {
         membersRow.style.display = "table-row";
      } else {
         membersRow.style.display = "none";
      }
   }
}