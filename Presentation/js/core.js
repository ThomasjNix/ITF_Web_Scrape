$('.dropdown-menu a').on('click', function(){
  $('#dropdown_year_button').text($(this).text()+' ');
  $('#dropdown_year_button').append('<span class="caret"></span>');
});


for (var i = 1; i <= 5; i++){
  var test_player = "<tr class=\"player-"+i+"\"><td>Test content</td><td>2000</td><td>32</td>"+
      "<td>32</td><td>32</td><td>32</td><td>32</td><td>32</td><td>32</td><td>32</td></tr>";
  $('table').append(test_player);
  $('tr[class^=\"player-\"] td').addClass('player-td');
}
