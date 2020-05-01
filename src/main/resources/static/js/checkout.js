let responseItems = "";
let items = getAllItemsFromDb();

function requestProductToCheckout() {
    $.ajax({
        url : 'http://localhost:8080/cart',
        type : 'post',
        contentType : 'application/json',
        data : JSON.stringify(items),
        success : function (success) {
            responseItems = JSON.parse(success);
            loadToShowPurchaseCondAndCalc();
        },
        error : function (error) {
            console.log(error);
        }
    });
}

function loadToShowPurchaseCondAndCalc() {
    let purchaseCondData = "";
    let purchaseCalcData = "";
    let gTotal = 0;
    responseItems.forEach(product => {
        const itemCount = getItemCount(product.id);
        let singleOrPlural = itemCount > 1 ? 's' : '';
        const eachTotal = itemCount * product.price;
        purchaseCondData += `<li>${itemCount} ${product.name}${singleOrPlural} = $${eachTotal}</li>`;
        gTotal += eachTotal;
    });
    purchaseCalcData += `$${gTotal} + $1.5 = $${gTotal + 1.5}`;
    document.getElementById("gTotal").setAttribute("value", gTotal + 1.5) ;
    document.getElementById("cartItemListString").setAttribute("value", JSON.stringify(items));
    document.getElementById("purchaseCond").innerHTML = purchaseCondData;
    document.getElementById("purchaseCalc").innerHTML = purchaseCalcData;
}

function getItemCount(id) {
    let indx = items.findIndex(x => x.id === id);
    if (indx !== -1) {
        return items[indx].count;
    }
    return 0;
}
