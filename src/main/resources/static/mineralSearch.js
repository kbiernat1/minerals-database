$(document).ready(function() {

    var apiRoot = 'http://localhost:8080/adminPanel/minerals';
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
        data.forEach(function (mineral) {
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
})
