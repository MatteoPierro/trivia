const fs = require('fs');

fs.readdir("./test", changeReceivedFiles);

function changeReceivedFiles(error, files) {
    if (error) {
        console.log(error);
        return;
    }

    files.filter(fileToApprove).forEach(approve);
}

const fileToApprove = (file) => file.indexOf(".received.txt") != -1;

const approve = (file) => {
    const approvedFile = file.replace("received", "approved");
    fs.rename(`./test/${file}`, `./test/${approvedFile}`, (err) => { if (err) throw err });
}