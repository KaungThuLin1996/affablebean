let responseItems = "";

function requestProduct() {
    let items = getAllItemsFromDb();
    $.ajax({
        url : 'http://localhost:8080/cart',
        contentType : 'application/json',
        type : 'post',
        data : JSON.stringify(items),
        success : function (success) {
            responseItems = JSON.parse(success);
            loadToShowProduct();
        },
        error : function (error) {
            console.log(error);
        }
    });
}

function loadToShowProduct() {
    let tableData = "";
    let gTotal = 0;
    responseItems.forEach((product, i) => {
        let count = i + 1;
        tableData += `<tr>`;
        tableData += `<td>${count}</td>`;
        tableData += `<td><img src="/product/${product.id}/image" alt="${product.name}" width="50" height="50" /></td>`;
        tableData += `<td>${product.name}</td>`;
        tableData += `<td>$${getItemCount(product.id) * product.price}</td>`;
        tableData += `<td><button class="btn btn-danger btn-sm" onclick="remove1Item(${product.id})"><i class="fas fa-minus"></i></button>`;
        tableData += ` <span>${getItemCount(product.id)}</span> `;
        tableData += `<button class="btn btn-primary btn-sm"  onclick="add1Item(${product.id})"><i class="fas fa-plus"></i></button>`;
        tableData += ` <button class="btn btn-warning btn-sm" onclick="delete1Item(${product.id})"><i class="fas fa-trash"></i></button></td>`;
        gTotal += getItemCount(product.id) * product.price;
    });
    if (gTotal > 0) {
        tableData += `<tr><td colspan="3" class="text-center">GRAND TOTAL</td><td>$${gTotal}</td></tr>`;
    }
    document.getElementById("tableBody").innerHTML = tableData;
}

function clearCart() {
    clearDb();
    location.reload();
}

function getItemCount(id) {
    let items = getAllItemsFromDb();
    let indx = items.findIndex(x => x.id === id);
    if (indx !== -1) {
        return items[indx].count;
    }
    return 0;
}

function add1Item(id) {
    addItemToDb(id);
    loadToShowProduct();
}

function remove1Item(id) {
    let items = getAllItemsFromDb();
    let indx = items.findIndex(x => x.id === id);
    if(indx !== -1) {
        if(items[indx].count > 1) {
            items[indx].count = items[indx].count - 1;
        }
    }
    saveItemToDb(items);
    loadToShowProduct();
}

function delete1Item(id) {
    let items = getAllItemsFromDb();
    let indx1 = items.findIndex(x => x.id === id);
    if (indx1 !== -1) {
        items.splice(indx1, 1);
    }
    saveItemToDb(items);
    let indx2 = responseItems.findIndex(x => x.id === id);
    if (indx2 !== -1) {
        responseItems.splice(indx2, 1);
    }
    loadToShowProduct();
}
