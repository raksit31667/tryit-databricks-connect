resource "azurerm_resource_group" "rg-databricks" {
  name     = "rg-databricks"
  location = "West US"
}

resource "azurerm_databricks_workspace" "databricks-connect-test" {
  name                = "databricks-connect-test"
  resource_group_name = azurerm_resource_group.rg-databricks.name
  location            = azurerm_resource_group.rg-databricks.location
  sku                 = "standard"

  tags = {
    Environment = "Development"
  }
}