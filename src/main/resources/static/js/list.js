$(function() {



    // nextリンククリック時のイベント処理設定 
     $('.next').on('click', function() {
      $('#searchForm [name=page]').val(currentPage + 1);　　//ページング
//      $('#searchForm [name=categoryName]').val(createCategoryName());
      $('#searchForm').submit();
    }); 

    // previousリンククリック時のイベント処理設定
    $('.previous').on('click', function() {
      $('#searchForm [name=page]').val(currentPage - 1);　//ページング
//      $('#searchForm [name=categoryName]').val(createCategoryName());
      $('#searchForm').submit();
    });

    // 全カテゴリー情報取得
//     $.getJSON('./categories')
//      .done(function(jsonCategories) {
//        daiCategories = jsonCategories;
//        createDaiCategorySelect();
//      })
//      .fail(function() {
//        console.log('失敗');
//      })
//      .always(function() {
//      });
	
});


//var daiCategories;
//var selectedDaiCategory;
//var chuCategories;
//var selectedChuCategory;
//var syoCategories;

// カテゴリーのプルダウン設定
// function createDaiCategorySelect() {
//  var daiOptions = '<option value="">- daiCategory -</option>';
//  for (let i = 0; i < daiCategories.length; i++) {
//    let daiCategory = daiCategories[i];
//    let selectedStr = $('#searchForm [name=daiCategoryId]').val() == daiCategory.id ? ' selected' : '';
//    daiOptions += '<option value="' + daiCategory.id + '"' + selectedStr + '>' + daiCategory.name + '</option>';
//  }
//  $('#daiSelect').html(daiOptions);
//  createChuCategorySelect();
//}  

//カテゴリーのプルダウンの選択文字列を連結
// function createCategoryName() {
//     let categoryName = '';
//     if ($('#daiSelect option:selected') && $('#daiSelect option:selected').val() != '') {
//       categoryName += $('#daiSelect option:selected').text();
//       if ($('#chuSelect option:selected').val() != '') {
//         categoryName += '/' + $('#chuSelect option:selected').text();
//         if ($('#syoSelect option:selected').val() != '') {
//           categoryName += '/' + $('#syoSelect option:selected').text();
//         }
//       }
//     }
//     return categoryName;
// } 
 
 
