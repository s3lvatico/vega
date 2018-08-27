package org.gmnz.vega;


import org.gmnz.vega.domain.Category;
import org.gmnz.vega.service.CategoryService;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Random;


public class CategoryServiceTest {

	private static Vega vega;



	@BeforeClass
	public static void initApplicationContext() {
		VegaSpringUtil.initSpring();
		vega = VegaFactory.getFactory().buildVega();
	}



	@Test
	public void getAllCategories() throws VegaException {
		CategoryService svc = vega.getCategoryService();
		List<Category> categories = svc.getAllCategories();
		System.out.println(categories);
	}



	@Test
	public void getAllCategoriesWithAllergens() throws VegaException {
		CategoryService svc = vega.getCategoryService();
		List<Category> categories = svc.getAllCategoriesWithAllergens();
		System.out.println(categories);
	}



	@Test(expected = VegaException.class)
	public void getCategoryByIdWithNull() throws VegaException {
		vega.getCategoryService().getCategoryById(null);
	}



	@Test(expected = VegaException.class)
	public void getCategoryByIdWithEmpty() throws VegaException {
		vega.getCategoryService().getCategoryById("");
	}



	@Test
	public void getCategoryByIdWithWrongId() throws VegaException {
		Category c = vega.getCategoryService().getCategoryById("wrong-id");
		Assert.assertNull(c);
	}



	@Test
	public void getCategoryById() throws VegaException {
		CategoryService svc = vega.getCategoryService();
		List<Category> categories = svc.getAllCategoriesWithAllergens();
		int numCategories = categories.size();
		int randomCategoryIndex = new Random().nextInt(numCategories);
		Category expected = categories.get(randomCategoryIndex);
		System.out.println("expected category : " + expected);
		String id = expected.getId();
		Category actual = svc.getCategoryById(id);
		System.out.println("actual category : " + actual);
		Assert.assertEquals(expected, actual);
	}



	@Test(expected = VegaException.class)
	public void createCategoryWNull() throws VegaException {
		CategoryService svc = vega.getCategoryService();
		svc.createCategory(null);
	}



	@Test(expected = VegaException.class)
	public void createCategoryWEmpty() throws VegaException {
		CategoryService svc = vega.getCategoryService();
		svc.createCategory("");
	}



	@Test
	public void createCategoryAlreadyExists() throws VegaException {
		CategoryService svc = vega.getCategoryService();
		List<Category> categories = svc.getAllCategoriesWithAllergens();
		int numCategories = categories.size();
		int randomCategoryIndex = new Random().nextInt(numCategories);
		Category dupe = categories.get(randomCategoryIndex);
		String categoryName = dupe.getName();
		String dupeId = svc.createCategory(categoryName);
		Assert.assertEquals(dupe.getId(), dupeId);
	}



	@Test
	public void createCategory() throws VegaException {
		CategoryService svc = vega.getCategoryService();
		String newCategoryId = svc.createCategory("sample");
		Category actual = svc.getCategoryById(newCategoryId);
		Assert.assertEquals(newCategoryId, actual.getId());

		svc.deepRemove(newCategoryId);
	}



	@Test(expected = VegaException.class)
	public void changeCategoryNameNullId() throws VegaException {
		CategoryService svc = vega.getCategoryService();
		svc.changeCategoryName(null, "bohpe");
	}



	@Test(expected = VegaException.class)
	public void changeCategoryNameNullName() throws VegaException {
		CategoryService svc = vega.getCategoryService();
		svc.changeCategoryName("cucu", null);
	}



	@Test
	public void changeCategoryNameInvalidTarget() throws VegaException {

		String srcCatName = "cat_source";
		String tgtCatName = "cat_target";

		CategoryService svc = vega.getCategoryService();
		String srcCatId = svc.createCategory(srcCatName);
		String tgtCatId = svc.createCategory(tgtCatName);

		svc.changeCategoryName(srcCatId, tgtCatName);

		Assert.fail("not yet implemented");
	}



	@Test
	public void changeCategory() throws VegaException {
		CategoryService svc = vega.getCategoryService();

		String dummyCategoryName = "dummyCategory";
		String newCategoryName = "dummyRenamedCategory";
		String newCategoryId = svc.createCategory(dummyCategoryName);
		svc.changeCategoryName(newCategoryId, newCategoryName);

		Category actualCategory = svc.getCategoryById(newCategoryId);
		Assert.assertEquals(newCategoryName, actualCategory.getName());

		svc.deepRemove(newCategoryId);

	}



	@AfterClass
	public static void afterClass() {
		System.out.println("end of test");
	}
}
