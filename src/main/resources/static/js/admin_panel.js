$(document).ready(function() {

  var apiRoot = 'http://localhost:8080/admin_panel/minerals';
  var datatableRowTemplate = $('[data-datatable-row-template]').children()[0];
  var mineralsContainer = $('[data-minerals-container]');

  // init
  getMinerals();

  function createElement(data) {
    var element = $(datatableRowTemplate).clone();

    element.attr('data-mineral-id', data.id);

    element.find('[data-mineral-name-section] [data-mineral-name-paragraph]').text(data.name);
    element.find('[data-mineral-name-section] [data-mineral-name-input]').val(data.name);

    element.find('[data-mineral-color-section] [data-mineral-color-paragraph]').text(data.color);
    element.find('[data-mineral-color-section] [data-mineral-color-input]').val(data.color);

    element.find('[data-mineral-shine-section] [data-mineral-shine-paragraph]').text(data.shine);
    element.find('[data-mineral-shine-section] [data-mineral-shine-input]').val(data.shine);

    element.find('[data-mineral-transparency-section] [data-mineral-transparency-paragraph]').text(data.transparency);
    element.find('[data-mineral-transparency-section] [data-mineral-transparency-input]').val(data.transparency);

    element.find('[data-mineral-fragility-section] [data-mineral-fragility-paragraph]').text(data.fragility);
    element.find('[data-mineral-fragility-section] [data-mineral-fragility-input]').val(data.fragility);

    element.find('[data-mineral-opalescence-section] [data-mineral-opalescence-paragraph]').text(data.opalescence);
    element.find('[data-mineral-opalescence-section] [data-mineral-opalescence-input]').val(data.opalescence);

    element.find('[data-mineral-region-section] [data-mineral-region-paragraph]').text(data.region);
    element.find('[data-mineral-region-section] [data-mineral-region-input]').val(data.region);
    return element;
  }

  function handleDatatableRender(data) {
    mineralsContainer.empty();
    data.forEach(function(mineral) {
      createElement(mineral).appendTo(mineralsContainer);
    });
  }

  function getMinerals() {
    var requestUrl = apiRoot;

    $.ajax({
      url: requestUrl,
      method: 'GET',
        success: handleDatatableRender
     });
  }

  function handleMineralUpdateRequest() {
    var parentEl = $(this).parent().parent();
    var mineralId = parentEl.attr('data-mineral-id');
    var mineralName = parentEl.find('[data-mineral-name-input]').val();
    var mineralColor = parentEl.find('[data-mineral-color-input]').val();
    var mineralShine = parentEl.find('[data-mineral-shine-input]').val();
    var mineralTransparency = parentEl.find('[data-mineral-transparency-input]').val();
    var mineralFragility = parentEl.find('[data-mineral-fragility-input]').val();
    var mineralOpalescence = parentEl.find('[data-mineral-opalescence-input]').val();
    var mineralRegion = parentEl.find('[data-mineral-region-input]').val();

    var requestUrl = apiRoot;

    $.ajax({
      url: requestUrl,
      method: "PUT",
      processData: false,
      contentType: "application/json; charset=utf-8",
      dataType: 'json',
      data: JSON.stringify({
        id: mineralId,
        name: mineralName,
        color: mineralColor,
        shine: mineralShine,
        transparency: mineralTransparency,
        fragility: mineralFragility,
        opalescence: mineralOpalescence,
        region: mineralRegion
      }),
      success: function(data) {
        parentEl.attr('data-mineral-id', data.id).toggleClass('datatable__row--editing');
        parentEl.find('[data-mineral-name-paragraph]').text(mineralName);
        parentEl.find('[data-mineral-color-paragraph]').text(mineralColor);
        parentEl.find('[data-mineral-shine-paragraph]').text(mineralShine);
        parentEl.find('[data-mineral-transparency-paragraph]').text(mineralTransparency);
        parentEl.find('[data-mineral-fragility-paragraph]').text(mineralFragility);
        parentEl.find('[data-mineral-opalescence-paragraph]').text(mineralOpalescence);
        parentEl.find('[data-mineral-region-paragraph]').text(mineralRegion);
      }
    });
  }

  function handleMineralDeleteRequest() {
    var parentEl = $(this).parent().parent();
    var mineralId = parentEl.attr('data-mineral-id');
    var requestUrl = apiRoot;

    $.ajax({
      url: requestUrl + '/' + mineralId,
      method: 'DELETE',
      success: function() {
        parentEl.slideUp(400, function() { parentEl.remove(); });
      }
    })
  }

  function handleMineralSubmitRequest(event) {
    event.preventDefault();

    var mineralName = $(this).find('[name="name"]').val();
    var mineralColor = $(this).find('[name="color"]').val();
    var mineralShine = $(this).find('[name="shine"]').val();
    var mineralTransparency = $(this).find('[name="transparency"]').val();
    var mineralFragility = $(this).find('[name="fragility"]').val();
    var mineralOpalescence = $(this).find('[name="opalescence"]').val();
    var mineralRegion = $(this).find('[name="region"]').val();

    var requestUrl = apiRoot;

    $.ajax({
      url: requestUrl,
      method: 'POST',
      processData: false,
      contentType: "application/json; charset=utf-8",
      dataType: 'json',
      data: JSON.stringify({
        name: mineralName,
        color: mineralColor,
        shine: mineralShine,
        transparency: mineralTransparency,
        fragility: mineralFragility,
        opalescence: mineralOpalescence,
        region: mineralRegion
      }),
      complete: function(data) {
        if(data.status === 200) {
          getMinerals();
        }
     }
    });
  }

  function toggleEditingState() {
    var parentEl = $(this).parent().parent();
    parentEl.toggleClass('datatable__row--editing');

    var mineralName = parentEl.find('[data-mineral-name-paragraph]').text();
    var mineralColor = parentEl.find('[data-mineral-color-paragraph]').text();
    var mineralShine = parentEl.find('[data-mineral-shine-paragraph]').text();
    var mineralTransparency = parentEl.find('[data-mineral-transparency-paragraph]').text();
    var mineralFragility = parentEl.find('[data-mineral-fragility-paragraph]').text();
    var mineralOpalescence = parentEl.find('[data-mineral-opalescence-paragraph]').text();
    var mineralRegion = parentEl.find('[data-mineral-region-paragraph]').text();

    parentEl.find('[data-mineral-name-input]').val(mineralName);
    parentEl.find('[data-mineral-color-input]').val(mineralColor);
    parentEl.find('[data-mineral-shine-input]').val(mineralShine);
    parentEl.find('[data-mineral-transparency-input]').val(mineralTransparency);
    parentEl.find('[data-mineral-fragility-input]').val(mineralFragility);
    parentEl.find('[data-mineral-opalescence-input]').val(mineralOpalescence);
    parentEl.find('[data-mineral-region-input]').val(mineralRegion);

  }

  $('[data-mineral-add-form]').on('submit', handleMineralSubmitRequest);

  mineralsContainer.on('click','[data-mineral-edit-button]', toggleEditingState);
  mineralsContainer.on('click','[data-mineral-edit-abort-button]', toggleEditingState);
  mineralsContainer.on('click','[data-mineral-submit-update-button]', handleMineralUpdateRequest);
  mineralsContainer.on('click','[data-mineral-delete-button]', handleMineralDeleteRequest);
});