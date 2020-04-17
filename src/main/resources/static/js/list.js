

$(function() {


	var daiCategories;
	var selectedDaiCategory;
	var chuCategories;
	var selectedChuCategory;
	var syoCategories;
	
	 // 現在のページ
    var currentPage = parseInt($('#searchForm [name=page]').val());
   	

    // nextリンククリック時のイベント処理設定
     $('.next').on('click', function() {
      $('#searchForm [name=page]').val(currentPage + 1);　　// ページング
      $('#searchForm [name=categoryName]').val(createCategoryName());
      $('#searchForm').submit();
    }); 

    // previousリンククリック時のイベント処理設定
    $('.previous').on('click', function() {
      $('#searchForm [name=page]').val(currentPage - 1);　// ページング
      $('#searchForm [name=categoryName]').val(createCategoryName());
      $('#searchForm').submit();
    });
    
 // カテゴリーリンククリック時のイベント処理設定
    $('.categoryLink').on('click', function() {
    $('#searchForm [name=categoryName]').val($(this).data('category'));　　// data('category')はhtmlのth:data-categoryに関連
    $('#searchForm [name=page]').val(1);
    $('#searchForm').submit();
  }); 
    
    // ブランド名リンククリック時のイベント処理設定
    $('.brandLink').on('click', function() {
    $('#searchForm [name=brand]').val($(this).text());
    $('#searchForm [name=page]').val(1);
    $('#searchForm').submit();
  });  
    
    // 大カテゴリープルダウン変更時のイベント処理設定　　3
    $('#daiSelect').on('change', function() {
     createChuCategorySelect();
   });  
    
 // 中カテゴリープルダウン変更時のイベント処理設定  5
    $('#chuSelect').on('change', function() {
   createSyoCategorySelect();
 });  
    
    // Goボタンクリック時のイベント処理設定
    $('#button-go').on('click', function() {
    $('#searchForm [name=categoryName]').val(createCategoryName());
    $('#searchForm').submit();
  });  
    
 // 検索ボタンクリック時のイベント処理設定
    $('#button-search').on('click', function() {
    $('#searchForm [name=page]').val(1);
  $('#searchForm [name=categoryName]').val(createCategoryName());
    $('#searchForm').submit();
  });  
    
     // 全カテゴリー情報取得 Ajax
     $.getJSON('./categories')
      .done(function(jsonCategories) {
        daiCategories = jsonCategories;
        createDaiCategorySelect();
      })
      .fail(function() {
        console.log('失敗');
      })
      .always(function() {
      });
	

// カテゴリーのプルダウン設定 //大カテゴリー　1
 function createDaiCategorySelect() {
  var daiOptions = '<option value="">- daiCategory -</option>';
  for (let i = 0; i < daiCategories.length; i++) {
    let daiCategory = daiCategories[i];
    let selectedStr = $('#searchForm [name=daiCategoryId]').val() == daiCategory.id ? ' selected' : '';
    daiOptions += '<option value="' + daiCategory.id + '"' + selectedStr + '>' + daiCategory.name + '</option>';
  }
  $('#daiSelect').html(daiOptions);
  createChuCategorySelect();
}  

// カテゴリーのプルダウンの選択文字列を連結  //プルダウン関係ない
 function createCategoryName() {
     let categoryName = '';    
     if ($('#daiSelect option:selected') && $('#daiSelect option:selected').val() != '') {  //カテゴリーが選択されてたら
       categoryName += $('#daiSelect option:selected').text();　//textの内容を取得　　other押したらotherを
       if ($('#chuSelect option:selected').val() != '') {
         categoryName += '/' + $('#chuSelect option:selected').text();　//DBのname_allにあわせてる
         if ($('#syoSelect option:selected').val() != '') {
           categoryName += '/' + $('#syoSelect option:selected').text();
         }
       }
     }
     return categoryName;
 } 
 
 // 中カテゴリーのプルダウン生成 2
 function createChuCategorySelect() {
 let selectedDaiCategoryValue = $('#daiSelect option:selected').val(); //セレクトされた値をセット
 if (selectedDaiCategoryValue != '') {
   for (let i = 0; i < daiCategories.length; i++) {
     let daiCategory = daiCategories[i];
     if (daiCategory.id == selectedDaiCategoryValue) {
       selectedDaiCategory = daiCategory;
       var chuOptions = '<option value="">- chuCategory -</option>';
       chuCategories = daiCategory.childCategories;　　//代入
       for (let j = 0; j < chuCategories.length; j++) {
         let chuCategory = chuCategories[j];
         let selectedStr = $('#searchForm [name=chuCategoryId]').val() == chuCategory.id ? ' selected' : '';
         chuOptions += '<option value="' + chuCategory.id + '"' + selectedStr + '>' + chuCategory.name + '</option>';
       }
       $('#chuSelect').html(chuOptions);
     }
   }
   createSyoCategorySelect();
 } else {
   $('#chuSelect').html('');
   $('#chuCategoryId').val('');
   $('#syoSelect').html('');
   $('#syoCategoryId').val('');
 }
}  
 
//小カテゴリーのプルダウン生成  4
 function createSyoCategorySelect() {
 let selectedChuCategoryValue = $('#chuSelect option:selected').val();
 if (selectedChuCategoryValue != '') {
   for (let i = 0; i < chuCategories.length; i++) {
     let chuCategory = chuCategories[i];
     if (chuCategory.id == selectedChuCategoryValue) {
       selectedChuCategory = chuCategory;
       var syoOptions = '<option value="">- syoCategory -</option>';
       syoCategories = chuCategory.childCategories;
       for (let j = 0; j < syoCategories.length; j++) {
         let syoCategory = syoCategories[j];
         let selectedStr = $('#searchForm [name=syoCategoryId]').val() == syoCategory.id ? ' selected' : '';
         syoOptions += '<option value="' + syoCategory.id + '"' + selectedStr + '>' + syoCategory.name + '</option>';
       }
       $('#syoSelect').html(syoOptions);
     }
   }
 } else {
   $('#syoSelect').html('');
   $('#syoCategoryId').val('');
 }
}  
 
 
 
 
});
